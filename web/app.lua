-- 
-- 
-- 
-- 
-- 

local app     = require('luvit-app'):new()
local table   = require 'table'
local string  = require 'string'
local json    = require 'json'
local restful = require './restful'

local headers = {['Content-type'] = 'application/json;charset=utf-8'}
local find, gsub, match, insert, stringify = string.find, string.gsub, string.match, table.insert, json.stringify

string, table, json = nil, nil, nil

-- static files
app:mount('/public/', 'static', {mount = '', root = __dirname .. '/public'})

app:use(restful({
    resources = {
      words = '/words%/?(%d*)$'
    }
  }))

--app:GET('/phrases%/?$', phrases)                -- list phrases
--app:GET('/([%w%d%_%-]*)/phrases%/?$', phrases)  -- phrases by author name e.g. /chapolin_colorado/phrases
--app:GET('/authors%/?$', authors)                -- authors list

app:run(8282, '0.0.0.0')
