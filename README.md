Timmy
=====

Timmy is a time-tracking app for Android.


To setup demo service:

    $ cd misc/demoservice
    $ npm install
    $ ./daemon.js

Visit http://localhost:8000/



    curl -X POST 'http://localhost:8000/tracks/new' -d 'description=hey niu track&start=1&end=2'

    {"track":
        {
         "id":"c80acb2f-a253-4f2e-bbc4-9fe09862592b",
         "description":"hey niu track",
         "start":1,
         "end":2,
         "duration":10,
         "resource":"Track"
         }
    }
