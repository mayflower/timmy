#!/usr/bin/env node

var resourceful = require('resourceful'),
    restful = require('restful'),
    Track = resourceful.define('track');

Track.property('description', String, { default: "niu track" });
Track.property('start', Number, { default: 10, minimum: 0, maximum: 20 });
Track.property('end', Number, { default: 10, minimum: 0, maximum: 20 });
Track.property('duration', Number, { default: 10, minimum: 0, maximum: 20 });

// Setup a standalone restful server
var server = restful.createServer([Track]);
server.listen(8000);
console.log(' > http server started on port 8000');;

