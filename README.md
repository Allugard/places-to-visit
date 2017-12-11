# places-to-visit
To run backend:
- in root directory mvn spring-boot:run -pl web

To run frontend:
1. cd web\ui
2. yarn install
3. yarn run start

## Docker

MariaDB:

docker run --name mariadb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=places -e MYSQL_PASSWORD=places -e MYSQL_DATABASE=places -d mariadb:latest
