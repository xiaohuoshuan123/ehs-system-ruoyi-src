# ============================================================
# RuoYi-Vue-Plus v6.0.0 部署镜像 (预构建版 v10)
# 前端 dist 本地预构建，后端在 Docker 内编译
# 适配 Render Free (512MB) 及以上
# ============================================================

# 阶段1: 编译后端（JDK）
FROM eclipse-temurin:21 AS backend-build
RUN apt-get update && apt-get install -y --no-install-recommends maven && rm -rf /var/lib/apt/lists/*
WORKDIR /build
COPY backend/. .
ENV MAVEN_OPTS=-Xmx512m
RUN mvn dependency:go-offline -B
RUN mvn clean package -DskipTests

# 阶段2: 运行（JRE）
FROM eclipse-temurin:21-jre
RUN apt-get update && apt-get install -y --no-install-recommends nginx wget curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY frontend/dist /usr/share/nginx/html
COPY --from=backend-build /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE 80
CMD CMD ["sh", "-c", "nginx -g 'daemon off;' & echo '[STARTUP] Starting Java...' && java -Dserver.port=8080 -Xms128m -Xmx384m -XX:+UseSerialGC -jar app.jar 2>&1 | tee /tmp/java.log; echo '[STARTUP] Java exited with code $?' >> /tmp/java.log"]
# rebuild trigger
