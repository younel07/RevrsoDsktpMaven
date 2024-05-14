# using the OpenJDK 17 image as the base image
FROM openjdk:17-alpine

WORKDIR /app

# Copie du fichier JAR vers le répertoire de travail
COPY Reverso_maven.jar /app/Reverso_maven.jar

# Débogage : Affichage du contenu du répertoire de travail pour vérifier la présence du fichier JAR
RUN ls -la

# Commande d'exécution du fichier JAR
CMD ["java", "-jar", "ReversoMaven-1.0-SNAPSHOT.jar"]
