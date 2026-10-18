# ============================================================
# RuoYi-Vue-Plus v6.0.0 部署镜像 (预构建版 v4)
# 前端 dist 本地预构建，后端在 Docker 内编译（低内存配置）
# ============================================================

# 阶段1: 编译后端（低内存 Maven）
FROM eclipse-temurin:21-jre AS backend-build
RUN apt-get update && apt-get install -y --no-install-recommends maven gettext && rm -rf /var/lib/apt/lists/*
WORKDIR /build
COPY backend/pom.xml .
RUN mvn dependency:go-offline -B -Xmx512m
COPY backend/. .
RUN mvn clean package -DskipTests -Xmx512m

# 阶段2: 运行
FROM eclipse-temurin:21-jre
RUN apt-get update && apt-get install -y --no-install-recommends nginx gettext && rm -rf /var/lib/apt/lists/*
WORKDIR /app

# 前端已预构建
COPY frontend/dist /usr/share/nginx/html

# 后端 JAR 从构建阶段复制
COPY --from=backend-build /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf.template

ENV SPRING_PROFILES_ACTIVE=prod \\
    PORT=80 \\
    JAVA_OPTS="-Xms512m -Xmx1500m -XX:+UseZGC"

EXPOSE 80

CMD ["sh","-c","envsubst '\${PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;' & exec java -Dserver.port=8080 \${JAVA_OPTS} -jar app.jar"]
