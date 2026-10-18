# ============================================================
# RuoYi-Vue-Plus v6.0.0 部署镜像 (预构建版 v4)
# 前端 dist 本地预构建，后端在 Docker 内编译
# ============================================================

# 阶段1: 编译后端
FROM eclipse-temurin:21-jre AS backend-build
RUN apt-get update && apt-get install -y --no-install-recommends maven && rm -rf /var/lib/apt/lists/*
WORKDIR /build
COPY backend/pom.xml .
RUN mvn dependency:go-offline -B -Xmx512m
COPY backend/. .
RUN mvn clean package -DskipTests -Xmx512m

# 阶段2: 运行
FROM eclipse-temurin:21-jre
RUN apt-get update && apt-get install -y --no-install-recommends nginx && rm -rf /var/lib/apt/lists/*
WORKDIR /app

# 前端已预构建（本地 pnpm run build）
COPY frontend/dist /usr/share/nginx/html

# 后端 JAR 从构建阶段复制
COPY --from=backend-build /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 80

CMD ["sh", "-c", "nginx -g 'daemon off;' & java -Dserver.port=8080 -Xms512m -Xmx1500m -XX:+UseZGC -jar app.jar"]
