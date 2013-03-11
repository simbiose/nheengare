#!/bin/lua

--[[
  parse nheengatu vocabulary
  format:
    [*nheengatu word*] (*class, class {...}*) *portuguese word*, *portuguese word*, {...} (*class*) {...}
  runs with luvit
  author @xxleite
--]]

local io, os, st, js = require 'io', require 'os', require 'string', require 'json'
local exit, open, read, match, sub, gsub, format, stringify, parse = os.exit, io.open, io.read, st.match, st.sub, st.gsub, st.format, js.stringify, js.parse
io, os, st, js = nil, nil, nil, nil

local file, out_file, lines

file = open('to_be_parsed', 'r')

if not file then
  print("could not open file") exit()
end

local x, pt_index_span, _, world, yrl_word, pt_word, vide, yrl_words, pt_words, pt_words_index, lng_classes =
      0, 10000, '', '', '', '', '', {}, {}, {}, 
  {
    artigo = 1,
    adjetivo = 2,
    numeral = 3,
    pronome = 4,
    verbo = 5,
    ["advérbio"] = 6,
    [""] = 7,
    ["conjunção"] = 8,
    ["interjeição"] = 9,
    substantivo = 10
  }

local function words_and_classes(fragment)
  local y, z, tmp_index, is_class, contains_class, contains_word, classified, last_classified, indexed_words = 0, 0, 0, false, false, false, {}, {}, {}
  --
  local function check_class(class_name)
    --
    if classified[class_name] then return end
    --
    if lng_classes[class_name] then
      tmp_index = #yrl_words[#yrl_words].grammatical + 1
      yrl_words[#yrl_words].grammatical[tmp_index] = {class=lng_classes[class_name], classification={}}
      last_classified[#last_classified+1]          = {class=lng_classes[class_name], classification={}}
    else
      print(format("class not found: %s", class_name))
    end
    --
    classified[class_name] = true
  end
  --
  local function parse_fragment(sep, _, piece)
    --
    if sep==")" then
      is_class = false
    elseif sep=="(" or _=="(" then
      is_class = true
    end
    -- return empty words
    if match(piece, "^%s*$") then return end
    -- trim words
    piece = match(piece, "^%s*(.-)%s*$")
    --
    if is_class then
      gsub(piece, "%s*([^%s%,]*)%s*%,*%s*", check_class)
    else
      -- create pt word if it does not exists
      if not pt_words_index[piece] then
        tmp_index                    = #pt_words + 1
        pt_words[tmp_index]          = {id=(tmp_index + pt_index_span), lang=2, write={}, equals={}, tradutions={{lang=2, translate={}}}, afi={}, source={1}, grammatical={}, examples={}}
        pt_words[tmp_index].write[1] = piece
        pt_words_index[piece]        = tmp_index + pt_index_span
      end

      tmp_index = pt_words_index[piece] - pt_index_span
      
      -- insert class to pt word
      if #last_classified>0 then
        for y=1, #pt_words[tmp_index].grammatical do
          if pt_words[tmp_index].grammatical[y].class==last_classified[#last_classified].class then
            contains_class = true
          end
        end
        if not contains_class then
          pt_words[tmp_index].grammatical[y+1] = last_classified[#last_classified]
        end
        contains_class = false
      end
      
      -- insert yrl word index to pt word
      if #pt_words[tmp_index].tradutions > 0 then
        tmp_index = pt_words_index[piece] - pt_index_span
        for y=1, #pt_words[tmp_index].tradutions[1] do
          if pt_words[tmp_index].tradutions[1].translate[y].word==#yrl_words then
            contains_word = true
          end
        end
      else
        tmp_index = pt_words_index[piece] - pt_index_span
        y = 0
      end
      if not contains_word then
        pt_words[tmp_index].tradutions[1].translate[y+1] = {word=#yrl_words, weigth=(1.1 - (y * 0.1))}
      end
      contains_word = false

      -- insert pt word index in yrl word
      if not indexed_words[piece] then
        tmp_index = #yrl_words[#yrl_words].tradutions[1].translate + 1
        yrl_words[#yrl_words].tradutions[1].translate[tmp_index] = {word=pt_words_index[piece], weigth=(1.1 - (tmp_index * 0.1))}
      end
      --
      indexed_words[piece] = true
    end
  end
  --
  gsub(fragment, "%s*([%,%)%(]?)%s*(%(?)([^%,%)$]*)", parse_fragment)
end

-- 
for line in file:lines() do
  -- skip blank lines, # lines and { lines
  if sub(line, 1, 1)~='#' and not match(line, "^%s*$") and not match(line, "{") then
    word = match(line, "%[([^%]]*)%]%s?")
    vide = match(line, "%(ver%)%s*%[([^%]]*)%]")
    if not vide then
      --
      yrl_word                = {id=(#yrl_words+1), lang=1, write={}, equals={}, tradutions={{lang=2, translate={}}}, afi={}, source={1}, grammatical={}, examples={}}
      yrl_word.write[1]       = word
      yrl_words[#yrl_words+1] = yrl_word
      --
      words_and_classes(sub(line, #word+3))
    end
  end
  --x = x+1
  --if x>20 then
  --  break
  --end
end

local output = '{"sources":[{"id":1,"tutors":["Eduardo de Almeida Navarro"],"urls":["http://tupi.fflch.usp.br/node/6",'..
               '"http://tupi.fflch.usp.br/sites/tupi.fflch.usp.br/files/CURSO%20DE%20L%C3%8DNGUA%20GERAL%20%28NHEENGATU%29.pdf"],'..
               '"ISBN":"978-85-912620-0-7"},{"id":2,"tutors":["Wikip\\u00e9dia"],"urls":["http://pt.wiktionary.org/wiki/Wikcion%C3%A1rio:P%C3%A1gina_principal"]}],"words":'

output = output .. sub(stringify(yrl_words), 1, -2)
output = output ..','.. sub(stringify(pt_words), 2, -1)

output = output .. ',"languages":[{"id":1,"name":"Nheengatu","iso":"yrl"},{"id":2,"name":"Portugu\\u00eas","iso":"por"}],"grammatical_class":[{"id":1,"name":"Artigo","classification"'..
                   ':[{"id":1,"name":"Definido"},{"id":2,"name":"Singular"},{"id":3,"name":"Plural"},{"id":4,"name":"Indefinido"}]},{"id":2,"name":"Adjetivo","classification"'..
                   ':[{"id":1,"name":"Biforme"},{"id":2,"name":"Uniforme"}]},{"id":3,"name":"Numeral","classification":[{"id":1,"name":"Coletivo"},{"id":2,"name":"Ordinal"},'..
                   '{"id":3,"name":"Multiplicativo"},{"id":4,"name":"Fracion\\u00e1rio"},{"id":5,"name":"Partitivo"},{"id":6,"name":"Romano"},{"id":7,"name":"Cardinal"}]},'..
                   '{"id":4,"name":"Pronome","classification":[{"id":1,"name":"Direto"},{"id":2,"name":"Indireto"},{"id":3,"name":"Preposicionado"},{"id":4,"name":"Comutativo"},'..
                   '{"id":5,"name":"Singular"},{"id":6,"name":"Plural"},{"id":7,"name":"Masculino"},{"id":8,"name":"Feminino"},{"id":9,"name":"Primeira Pessoa"},{"id":10,"name":"Segunda Pessoa"}'..
                   ',{"id":11,"name":"Terceira Pessoa"},{"id":12,"name":"Pessoal"},{"id":13,"name":"Possessivo"},{"id":14,"name":"Adjetivo"},{"id":15,"name":"Substantivo"},'..
                   '{"id":16,"name":"Indefinido"},{"id":17,"name":"Relativo"},{"id":18,"name":"Interrogativo"},{"id":19,"name":"Adjunto"},{"id":20,"name":"Absoluto"},'..
                   '{"id":21,"name":"Sujeito"}]},{"id":5,"name":"Verbo","classification":[{"id":1,"name":"Intransitivo"},{"id":2,"name":"Impessoal"},{"id":3,"name":"Liga\\u00e7\\u00e3o"}'..
                   ',{"id":4,"name":"Primeira Conjuga\\u00e7\\u00e3o"},{"id":5,"name":"Segunda Conjugação"},{"id":6,"name":"Terceira Conjuga\\u00e7\\u00e3o"},{"id":7,"name":"Regular"},'..
                   '{"id":8,"name":"Irregular"},{"id":9,"name":"An\\u00f4malo"},{"id":10,"name":"Defectivo"},{"id":11,"name":"Abundante"},{"id":12,"name":"N\\u00famero"},'..
                   '{"id":13,"name":"Pessoa"},{"id":14,"name":"Modo"},{"id":15,"name":"Tempo"},{"id":16,"name":"Voz"},{"id":17,"name":"Infinitivo"},{"id":18,"name":"Partic\\u00edpio"}'..
                   ',{"id":19,"name":"Gerúndio"},{"id":20,"name":"Transitivo"}]},{"id":6,"name":"Adv\\u00e9rbio","classification":[{"id":1,"name":"Diminutivo"},{"id":2,"name":"Indicativo"}'..
                   ',{"id":3,"name":"Imperativo"},{"id":4,"name":"Subjuntivo"},{"id":5,"name":"Modo"},{"id":6,"name":"Lugar"},{"id":7,"name":"Tempo"},{"id":8,"name":"Nega\\u00e7\\u00e3o"}'..
                   ',{"id":9,"name":"Afirma\\u00e7\\u00e3o"},{"id":10,"name":"Dúvida"},{"id":11,"name":"Intensidade"},{"id":12,"name":"Quantidade"},{"id":13,"name":"Superlativo"}]},'..
                   '{"id":7,"name":"Preposi\\u00e7\\u00e3o","classification":[{"id":1,"name":"Acidental"},{"id":2,"name":"Contra\\u00e7\\u00e3o"},{"id":3,"name":"Imperfei\\u00e7\\u00e3o"},'..
                   '{"id":4,"name":"Essencial"}]},{"id":8,"name":"Conjun\\u00e7\\u00e3o","classification":[{"id":1,"name":"Aditiva"},{"id":2,"name":"Adversativa"},'..
                   '{"id":3,"name":"Alternativa"},{"id":4,"name":"Disjuntiva"},{"id":5,"name":"Proporcional"},{"id":6,"name":"Temporal"},{"id":7,"name":"Explicativa"},'..
                   '{"id":8,"name":"Conclusiva"},{"id":9,"name":"Subordinativa"},{"id":10,"name":"Integrante"},{"id":11,"name":"Causal"},{"id":12,"name":"Comparativa"},'..
                   '{"id":13,"name":"Concessiva"},{"id":14,"name":"Condicional"},{"id":15,"name":"Conformativa"},{"id":16,"name":"Consecutiva"},{"id":17,"name":"Final"},'..
                   '{"id":18,"name":"Coordenativa"}]},{"id":9,"name":"Interjei\\u00e7\\u00e3o","classification":[]},{"id":10,"name":"Substantivo","classification":'..
                   '[{"id":1,"name":"Derivado"},{"id":2,"name":"Simples"},{"id":3,"name":"Composto"},{"id":4,"name":"Pr\\u00f3prio"},{"id":5,"name":"Comum Concreto"},'..
                   '{"id":6,"name":"Comum Abstrato"},{"id":7,"name":"Comum Coletivo"},{"id":8,"name":"Biforme"},{"id":9,"name":"Heter\\u00f3nimo"},{"id":10,"name":"Uniforme Epiceno"}'..
                   ',{"id":11,"name":"Uniforme Comum"},{"id":12,"name":"Uniforme Sobrecomum"},{"id":13,"name":"Singular"},{"id":14,"name":"Plural"},{"id":15,"name":"Primitivo"}]}]}'

if parse(output) then
  print("")
  print("json data ok!")
end

print(format("%d words in nheengatu and %d words in portuguese, %d characters of raw json data", #yrl_words, #pt_words, #output))

out_file = open('../database/db.json', 'w')
if out_file then
  out_file:write(output)
  out_file:close()
end

print("done, db.json generated")