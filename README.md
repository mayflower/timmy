Timmy
=====

Timmy is a time-tracking app for Android.


To setup demo service:

    $ cd misc/demoservice
    $ npm install
    $ ./daemon.js

Visit http://localhost:8000/



    curl -X POST 'http://localhost:8000/tracks/new' -H 'Content-Type: application/json;' -d '{"description":"hey niu niu track", "start":"2013-02-01T12:10:10.123Z", "end":"2013-02-01T14:10:10.123Z"}'

