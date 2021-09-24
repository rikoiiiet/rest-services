# rest-services

1. prereqisites
- java 8 or higher
- apache maven
- git
- open liberty server

2. setup as a regular bin
- download open liberty https://openliberty.io/
- unarchive to some_folder
- you can start defaultServer that comes with open liberty
```
/<some_folder>/wlp/bin/server run
```
- or you can create your own server and start it
```
/<some_folder>/wlp/bin/server create some_server
/<some_folder>/wlp/bin/server start some_server
```
- clone this repo
- create artifact with maven
```
mvn clean package
```
- copy server.xml and jcc-11.5.6.0.jar to /<some_folder>/wlp/usr/servers/<some_server>
- copy artifact target/*.war to /<some_folder>/wlp/usr/servers/<some_server>/dropins
