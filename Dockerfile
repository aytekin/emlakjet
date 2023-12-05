FROM openjdk:17-oracle
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]