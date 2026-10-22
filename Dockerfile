# RuoYi-Vue-Plus Deploy Image (v12 - Simple Java Only)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY frontend/dist /usr/share/nginx/html
COPY --chdir=backend/ruoyi-admin/target ruoyi-admin.jar app.jar
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 8080
CMD ["java", "-Dserver.port=8080", "-Xms128m", "-Xmx384m", "-XX:+UseSerialGC", "-jar", "app.jar"]
