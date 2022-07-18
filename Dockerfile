FROM openjdk:8

COPY target/survivorcamp.jar survivorcamp.jar

ENTRYPOINT ["java","-jar","survivorcamp.jar"]