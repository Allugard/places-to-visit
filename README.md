# places-to-visit

To run frontend:
1. cd web\ui
2. yarn install
3. yarn run start

## Docker

1. docker run --name mariadb -p 3306:3306 -p 9001:9001 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=places -e MYSQL_PASSWORD=places -e MYSQL_DATABASE=places -d mariadb:latest
2. mvn clean install dockerfile:build -pl web
3. docker run -d --name web --network container:mariadb web:latest

# Integration tests

mvn -Dtest="yo.antihype.team.docker.**" test -pl web



 

