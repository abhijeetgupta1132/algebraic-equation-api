FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# copy project files
COPY . .

# build the jar inside container
RUN ./mvnw clean package -DskipTests

# run the jar
ENTRYPOINT ["java","-jar","target/*.jar"]
