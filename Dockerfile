FROM openjdk:17-slim
#если каталог есть, то перейдет, иначе создаст и перейдет
WORKDIR /opt/app
COPY target/http-server-1.0.jar app.jar
CMD ["java", "-jar", "app.jar"]