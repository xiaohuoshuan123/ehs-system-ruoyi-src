# ============================================================
# RuoYi-Vue-Plus v6.0.0 Deploy Image (v14)
# ============================================================
FROM eclipse-temurin:21 AS backend-build
RUN apt-get update && apt-get install -y --no-install-recommends maven && rm -rf /var/lib/apt/lists/*
WORKDIR /build
COPY backend/. .
ENV MAVEN_OPTS=-Xmx512m
RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
RUN apt-get update && apt-get install -y --no-install-recommends nginx wget curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY frontend/dist /usr/share/nginx/html
COPY --from=backend-build /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 80
CMD ["/app/entrypoint.sh"]
