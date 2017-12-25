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
1.cd web
2. mvn clean install dockerfile:build
3. docker-compose up  

