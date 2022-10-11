FROM maven:3.6.3-jdk-11-openj9
WORKDIR /person
COPY . .
RUN mvn clean install
EXPOSE 8080
CMD mvn spring-boot:run