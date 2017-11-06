build: mvn clean compile
run-tomcat: mvn spring-boot:run

Postman POST: http://localhost:8080/api/v1/projectname/sample

visualizar documentação da api com SwaggerUI
Pasta swagger
http-server -p [porta] --cors -d 
-----------------------------------------
http://localhost/#/
http://localhost:[porta]/biometric-engine-api-contract-1.0.0-snapshot.yaml