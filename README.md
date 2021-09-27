## rest-services

# 1. prereqisites
- java 8 or higher
- apache maven
- git
- open liberty server
- db of choice

# 2. setup as a regular bin
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
- clone this repo and cd into folder
```
git clone https://github.com/<this-repo>/<this-app>.git
cd <this-app>
```
- create artifact with maven
```
mvn clean package
```
- copy server.xml and jcc-11.5.6.0.jar to /<some_folder>/wlp/usr/servers/<some_server>
```
cp src/main/liberty/config/server.xml /<some_folder>/wlp/usr/servers/<some_server>
cp src/main/liberty/config/jcc-11.5.6.0.jar /<some_folder>/wlp/usr/servers/<some_server>
```
- copy artifact target/*.war to /<some_folder>/wlp/usr/servers/<some_server>/dropins
```
cp target/*.war /<some_folder>/wlp/usr/servers/<some_server>/dropins
```
# 3. run as docker image
- download and run docker image with env variables
```
docker run -d -e DB_SERVER=<db-serverName-or-IP> -e DB_PORT=<db-port> \
-e DB_USERNAME=<db-username> -e DB_PASSWORD=<db-password> \
-e REST_USERNAME=<access-username> -e REST_PASSWORD=<access-password> -p <port-to-listen>:5050 rdpgests:latest
```
