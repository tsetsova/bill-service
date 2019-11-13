# bill-service
A Clojure microservice I started to experiment with and learn more about Clojure and Liberator. 

It's a tip calculator, based on Exercises for Programmers: 57 Challenges to Develop Your Coding Skills.

## Usage

To run the tests:
```
lein kaocha
```

To star the database:

run either

```
pg_ctl -D /usr/local/var/postgres start
```

or if on a Mac run:

```
brew services start postgresql
```

To start the server run:
```
lein ring server
```

This will open the browser on the index page at port 3000. 

If you navigate to /bills you'll see there are no bills yet
```
[]
```

To post a new bill (e.g. a bill for 30 GBP, where the tip percentage is 10%) via the command line:
```
curl -XPOST -d '{"amount": 30, "tip-percentage": 10}' -H 'Content-Type: application/json' localhost:3000/bills
```

If you reload the /bills endpoint, you'll now see the bills has been added

```
[{"id":1,"amount":30,"tip-percentage":10,"total":33.0}]
```

You can also query by id for example /bills/1
```
{"id":1,"amount":30,"tip-percentage":10,"total":33.0}
```


## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
