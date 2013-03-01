#!/usr/bin/env node

var resourceful = require('resourceful'),
    restful = require('restful'),
    Track = resourceful.define('track');

Track.property('description', String, { default: "no description" });
Track.property('start',       String, { format: 'date-time' });
Track.property('end',         String, { format:  'date-time' });
Track.property('duration',    Number, { default: 0, minimum: 0, maximum: 3600 });

// Setup a standalone restful server
var server = restful.createServer([Track]);
server.listen(8000);
console.log(' > http server started on port 8000');;

