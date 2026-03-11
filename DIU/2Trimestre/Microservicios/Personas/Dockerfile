# Usamos la misma base de Java 21 que en Tutoriales
FROM eclipse-temurin:21-jre

# Copiamos el JAR generado por Maven al contenedor
# ¡Ojo! Asegúrate de que el nombre coincida con el de tu pom.xml
COPY target/Personas-0.0.1-SNAPSHOT.jar /personasapp.jar

# Comando para arrancar la aplicación
CMD ["java", "-jar", "/personasapp.jar"]