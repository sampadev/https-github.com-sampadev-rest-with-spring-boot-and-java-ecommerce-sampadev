# Estágio de Build (construir a aplicação)
# Use uma imagem Java base leve, compatível com seu projeto Java 21
FROM maven:3.9-openjdk-21-slim AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml para o container para otimizar o cache do Maven
COPY pom.xml .

# Copia o código fonte da aplicação
COPY src ./src

# Empacota a aplicação Spring Boot em um JAR executável
# O -DskipTests pula os testes para um build mais rápido no Docker
RUN mvn clean install -DskipTests

# --- Estágio de Execução (rodar a aplicação) ---
# Use uma imagem JRE (runtime environment) leve para a execução, sem ferramentas de build
FROM openjdk:21-slim

WORKDIR /app

# Copia o JAR executável do estágio de build
COPY --from=builder /app/target/sampastore-0.0.1-SNAPSHOT.jar sampastore.jar

# Expõe a porta que sua aplicação Spring Boot vai usar
EXPOSE 8080

# Define o comando para iniciar a aplicação quando o container for executado
CMD ["java", "-jar", "sampastore.jar"]