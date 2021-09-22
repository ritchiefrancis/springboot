FROM maven:3-jdk-8

COPY pom.xml /home/app/
COPY src/main/java/ /home/app/src/main/java/
COPY src/main/resources/ /home/app/src/main/resources/

WORKDIR /home/app/

CMD ["mvn", "clean" ,"install", "spring-boot:run", "-DskipTests"]

