FROM openjdk:17-slim as builder

#Mettre a jour les paquets et installer maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY ../ /app

RUN mvn clean package

FROM openjdk:17-slim

COPY --from=builder /app/target/*.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "/app/application.jar"]
