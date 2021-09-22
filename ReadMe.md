The service uses MySql database. I've used docker to inflate the db and have included an example command if required.


To run both services:

 mvn clean install spring-boot:run

 
User Micrservice
http://localhost:8090/swagger-ui/index.html


Zuul Gateway
Example:
http://localhost:8080/user-service/<A VALID ID>


Example docker command to initialise the database:
docker run -v /my/own/datadir:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=users -p 3306:3306 mysql 