# gorilla-demo

Demo of Gorilla REPL along with Vega.  This particular demo uses US population from 2000-2015 to demonstrate graphing in Gorilla.  Requires modified Gorilla REPL available from https://github.com/pkimbrel

## Build

Checkout the following repositories:
* https://github.com/pkimbrel/gorilla-plot
* https://github.com/pkimbrel/gorilla-repl
* https://github.com/pkimbrel/lein-gorilla

For each project install them in your local Maven repository

`lein install`

From there, checkout this project and execute:

`lein gorilla :port 8080 :ip <your-ip-address>`

If you're running locally, you can use 127.0.0.1 or leave the IP option off.  However, if you intend to use the REPL on a server you will have to put the server IP on the start up command.

## Demo Usage

Once started, you can access the REPL:

http://<your-address>:8080/worksheet.html

To view the popluation report output:

http://<your-address>:8080/worksheet.html?filename=src/gorilla_demo/population-report.clj

If you want to start making changes to the formulas, you will need to load the database and the dependent worksheets.

First you will have to load the database.  This is done in `src/gorilla_demo/db.clj`.  For example, using EMACS, connect to the running Gorilla REPL (SPC M S C / localhost / Gorilla REPL) and execute `db.clj` (CTL C K).  Then uncomment the `(load-database)` and execute it directly (CTL C C).

**NOTE**: "load-database" is commented out to ensure the clojure file and dependencies load prior to loading the database.  The actual load will take approximately *5 minutes*!  If the REPL is restarted, the database will need to be reloaded.

Next you will have to execute the following worksheets:

* http://<your-address>:8080/worksheet.html?filename=src/gorilla_demo/population.clj
* http://<your-address>:8080/worksheet.html?filename=src/gorilla_demo/population-graph.clj
* http://<your-address>:8080/worksheet.html?filename=src/gorilla_demo/population-choropleth.clj

For each worksheet you hit CTL-SHIFT-ENTER which will run everything in the worksheet.  Let each worksheet complete before moving on to the next.

From there you should be able to re-execute the report worksheet:

* http://<your-address>:8080/worksheet.html?filename=src/gorilla_demo/population-report.clj

## License

Gorilla is licensed to you under the MIT licence. See LICENCE.txt for details.

Copyright © 2014- Jony Hudson and contributors
