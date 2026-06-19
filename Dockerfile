# Fase 1: Construcción (Build) usando Java 25
FROM maven:3.9-eclipse-temurin-25-alpine AS build
WORKDIR /app

# Copiamos el pom y las fuentes del proyecto
COPY pom.xml .
COPY src ./src

# Compilamos saltándonos los tests para acelerar el despliegue en Render
RUN mvn clean package -DskipTests

# Fase 2: Ejecución (Runtime) usando el JRE de Java 25
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copiamos el JAR generado desde la fase anterior usando los datos de tu pom.xml
COPY --from=build /app/target/DBSpringBoot-0.0.1-SNAPSHOT.jar app.jar

# Render mapea el puerto 8080 por defecto para servicios web de Spring Boot
EXPOSE 8080

# Limitación de memoria optimizada para contenedores pequeños/gratuitos de Render
ENV JAVA_OPTS="-Xmx300m -Xss512k"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
