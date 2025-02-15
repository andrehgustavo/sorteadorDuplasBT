# Usa a imagem do Java 17 com Alpine (mais leve)
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven/Gradle para dentro do container
COPY target/*.jar app.jar


# Expõe a porta 8080 (ou a porta que seu Spring Boot usa)
EXPOSE 8080

# Comando para rodar o aplicativo Spring Boot
CMD ["java", "-jar", "app.jar"]
