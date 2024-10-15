# 17-slim == image dispo dans l'image openjdk de docker (voir doc)
FROM openjdk:17-slim AS builder

WORKDIR /app

# copie tout sur le dossier courant
COPY . .

RUN ./gradlew bootjar

CMD ["java", "-jar", "build/libs/spring.security-0.0.1-SNAPSHOT.jar"]

#
# FROM openjdk:17-slim

#WORKDIR /app

# copie
#COPY --from=builder /app/build/libs/*.jar /app

#CMD ["java", "-jar", "spring.security.jar"]