# places-to-visit
To run backend locally:
in root directory
mvn spring-boot:run -pl web

To run frontend:
1. cd web\ui
2. yarn install
3. yarn run start

## Docker

app:
1. mvn clean install dockerfile:build -pl web
2. docker run --name web -p 9001:9001 web:latest

MariaDB:

docker run --name mariadb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=places -e MYSQL_PASSWORD=places -e MYSQL_DATABASE=places -d mariadb:latest
