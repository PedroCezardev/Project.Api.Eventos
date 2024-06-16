# Usar uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo pom.xml e os arquivos de código-fonte para o contêiner
COPY pom.xml .
COPY src ./src

# Instalar o Maven e compilar o projeto
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

# Expor a porta que a aplicação Spring Boot usa
EXPOSE 8080

# Mover o .jar gerado para o local correto
RUN mv target/*.jar app.jar

# Comando para executar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]