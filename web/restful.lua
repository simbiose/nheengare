--
--
--
--

local string = require 'string'
local table  = require 'table'
local path   = require 'path'

local lower, match, gsub, find, insert = string.lower, string.match, string.gsub, string.find, table.insert

string, table = nil, nil

-- 
local function parse_query_string(query_string)
  local results     = {}
  local parse_query = function(key, value)
    value = tonumber(value) or value
    if find(key, "%[%]") ~= nil then
      key          = gsub(key, '%[%]', '')
      results[key] = results[key] and results[key] or {}
      insert(results[key], value)
    else
      results[key] = value
    end
  end
  gsub(query_string, "%/?%??([^&=]+)=?([^&=]*)&?", parse_query)
  return results
end

--local function 

return function (options)
  -- defaults
  local verbs, options, controllers = 
    {get='show', post='update', put='create', delete='delete'}, (options or {}), {}

  --
  local resources     = options.resources or {}
  options.resources   = {}
  options.root_folder = options.root_folder or './controllers'

  -- load all controllers
  for key, value in pairs(resources) do
    local _, controller = pcall(require, path.join(options.root_folder, key))
    if _ then
      options.resources[controller:new()] = value
    end
  end

  -- call method
  local function call_method(self, action, res, query, ...)
    if #{...}>0 then
      self[action](self, res, query, ...)
      return true
    end
    return false
  end

  -- handler
  return function (req, res, nxt)

    -- determine action
    local action, query = 
      (verbs[lower(req.headers['X-HTTP-Method-Override'] or req.method)] or 'show'), parse_query_string(req.uri.search)

    --
    for key, value in pairs(options.resources) do
      if type(value)=='table' then
        for x in 1,#value do
          if call_method(key, action, res, query, match(req.uri.pathname, value[x])) then 
            return 
          end
        end
      else
        if call_method(key, action, res, query, match(req.uri.pathname, value)) then 
          return 
        end
      end
    end
    nxt()
  end
end