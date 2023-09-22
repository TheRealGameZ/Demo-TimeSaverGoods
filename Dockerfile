
FROM maven AS build
COPY . /home/app/src
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:20
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
