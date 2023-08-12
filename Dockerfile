# Utilisez une image de base avec Maven pour la construction
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Définissez le répertoire de travail
WORKDIR /app

# Copiez le fichier pom.xml pour installer les dépendances
COPY pom.xml .

# Téléchargez les dépendances de votre application
RUN mvn dependency:go-offline

# Copiez le reste du code source
# COPY src/ /app/src/

# Compilez et construisez le fichier JAR
# RUN mvn package
CMD ["mvn", "spring-boot:run"]

# Utilisez une image de base avec Java pour l'exécution
# FROM openjdk:17

# Définissez le répertoire de travail
# WORKDIR /app

# Copiez le fichier JAR généré à partir de l'étape précédente
# COPY --from=build /app/target/*.jar app.jar

# Commande pour exécuter l'application Spring Boot
# CMD ["java", "-jar", "app.jar"]
