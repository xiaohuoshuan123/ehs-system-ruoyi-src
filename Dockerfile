# ============================================================
# RuoYi-Vue-Plus v6.0.0 部署镜像 (修复版)
# 关键修复:
#   1) 基础镜像改为 JDK 21 (pom.xml 指定 java.version=21, JDK17 无法编译/运行)
#   2) nginx 监听 Render 注入的 $PORT (原硬编码 80, Render 无法路由/健康检查)
#   3) 默认激活 prod profile (原未设置, 会走 dev 去连 localhost 库)
#   4) 通过环境变量覆盖 MySQL/Redis 地址, 不再写死 localhost
# 注: RuoYi-Vue-Plus 模块极多, 在 Render 内 mvn 构建易 OOM/超时.
#     更稳方案: 本地 `mvn -pl ruoyi-admin -am clean package -DskipTests`
#     后改用下方"预构建"版本 (只 ADD jar, 注释掉阶段1).
# ============================================================

# ---------- 阶段1: 编译后端 ----------
FROM eclipse-temurin:21-jdk AS backend
WORKDIR /build
RUN apt-get update && apt-get install -y --no-install-recommends maven && rm -rf /var/lib/apt/lists/*
COPY backend/pom.xml .
RUN mvn -q -B dependency:go-offline -pl ruoyi-admin -am || true
COPY backend/. .
ENV MAVEN_OPTS="-Xmx1024m -XX:+UseG1GC"
RUN mvn -q -B clean package -DskipTests -pl ruoyi-admin -am

# ---------- 阶段2: 运行 ----------
FROM eclipse-temurin:21-jre AS runtime
RUN apt-get update && apt-get install -y --no-install-recommends nginx && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY frontend/dist /usr/share/nginx/html
COPY --from=backend /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf.template

ENV SPRING_PROFILES_ACTIVE=prod \
    PORT=80 \
    JAVA_OPTS="-Xms512m -Xmx1500m -XX:+UseZGC"

EXPOSE 80

CMD ["sh","-c","envsubst '${PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;' & exec java -Dserver.port=8080 ${JAVA_OPTS} -jar app.jar"]

# ---------- 预构建版本 (推荐, 把上面阶段1 + 阶段2 的第一段替换为): ----------
# FROM eclipse-temurin:21-jre
# RUN apt-get update && apt-get install -y --no-install-recommends nginx && rm -rf /var/lib/apt/lists/*
# WORKDIR /app
# COPY frontend/dist /usr/share/nginx/html
# COPY backend/ruoyi-admin/target/ruoyi-admin.jar app.jar
# COPY nginx.conf /etc/nginx/nginx.conf.template
# ENV SPRING_PROFILES_ACTIVE=prod PORT=80 JAVA_OPTS="-Xms512m -Xmx1500m -XX:+UseZGC"
# EXPOSE 80
# CMD ["sh","-c","envsubst '${PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;' & exec java -Dserver.port=8080 ${JAVA_OPTS} -jar app.jar"]
