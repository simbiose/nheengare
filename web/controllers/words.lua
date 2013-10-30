--
--
--
--

local object = require('core').Object

local words = object:extend()

-- show word by id or many
function words:show(req, query, ...)
  p('show, sending')
  req:send(200, 'foo master [show]', {['Content-type'] = 'application/json;charset=utf-8'})
end

-- add word
function words:add(req, query, ...)
  p('create')
  req:send(200, 'foo master [creat]')
end

-- update word
function words:update(req, query, ...)
  p('update')
  req:send(200, 'foo master [update]')
end

-- delete word
function words:delete(req, query, ...)
  p('delete')
  req:send(200, 'foo master [delete]')
end

return words