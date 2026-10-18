# ============================================================
# RuoYi-Vue-Plus v6.0.0 部署镜像 (预构建版 v3)
# 为什么改预构建: Render 的 Docker 构建环境仅 512Mi, 在镜像内
#   `mvn package` 编译多模块必 OOM (报错 used over 512Mi)。
#   改为: 后端 jar / 前端 dist 都在本地(WSL)提前打好,
#   这里只负责 COPY + 运行, 构建期几乎零内存。
# 本地出包(WSL):
#   cd backend && mvn -pl ruoyi-admin -am clean package -DskipTests
#   mkdir -p ../deploy && cp ruoyi-admin/target/ruoyi-admin.jar ../deploy/ruoyi-admin.jar
#   cd ../frontend && npm install && npm run build:prod
# ============================================================

FROM eclipse-temurin:21-jre
RUN apt-get update && apt-get install -y --no-install-recommends nginx gettext && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY frontend/dist /usr/share/nginx/html
COPY deploy/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf.template

ENV SPRING_PROFILES_ACTIVE=prod \
    PORT=80 \
    JAVA_OPTS="-Xms512m -Xmx1500m -XX:+UseZGC"

EXPOSE 80

CMD ["sh","-c","envsubst '${PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;' & exec java -Dserver.port=8080 ${JAVA_OPTS} -jar app.jar"]
