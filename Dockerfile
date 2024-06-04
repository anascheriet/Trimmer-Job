FROM openjdk:17-jdk

WORKDIR /app

COPY . /app
COPY build/resources/main/files/input.txt /app
COPY build/libs/trimmer-0.0.1-SNAPSHOT.jar /app/trimmer-0.0.1-SNAPSHOT.jar


CMD ["java", "-jar", "trimmer-0.0.1-SNAPSHOT.jar"]
