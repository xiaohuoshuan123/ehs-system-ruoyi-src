# ============================================================
# RuoYi-Vue-Plus v6.0.0 部署镜像 (修复版 v2)
# 关键修复:
#   1) 基础镜像 JDK 21 (pom.xml 指定 java.version=21, JDK17 无法运行)
#   2) 先 COPY 整个 backend 再 mvn 构建 (否则 reactor 找不到子模块,
#      报 "Child module does not exist" / "ruoyi-common-bom 找不到")
#   3) 固定 Maven 3.9.9 (apt 旧版 3.6.3 跑 Java21 + enforcer 会失败)
#   4) nginx 监听 Render 注入的 $PORT; 默认激活 prod profile
#   5) 通过环境变量注入 MySQL/Redis 地址, 不再写死 localhost
# 注: RuoYi-Vue-Plus 模块极多, 在 Render 内 mvn 构建可能耗时 15~25 分钟,
#     若触发构建超时/OOM, 改用文末"预构建"版本(本地出 jar 再 ADD).
# ============================================================

# ---------- 阶段1: 编译后端 ----------
FROM eclipse-temurin:21-jdk AS backend
WORKDIR /build
# 安装固定版本 Maven 3.9.9 (兼容 Java 21 + RuoYi enforcer)
RUN apt-get update && apt-get install -y --no-install-recommends wget ca-certificates && \
    wget -q https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz -O /tmp/mvn.tgz && \
    tar xzf /tmp/mvn.tgz -C /opt && \
    ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn && \
    rm -f /tmp/mvn.tgz && rm -rf /var/lib/apt/lists/*

# 关键修复: 先把整个 backend 拷进来, 让 reactor 能看到所有子模块
COPY backend/. .
ENV MAVEN_OPTS="-Xmx2048m -XX:+UseG1GC"
RUN mvn -q -B clean package -DskipTests -pl ruoyi-admin -am

# ---------- 阶段2: 运行 ----------
FROM eclipse-temurin:21-jre AS runtime
RUN apt-get update && apt-get install -y --no-install-recommends nginx gettext && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY frontend/dist /usr/share/nginx/html
COPY --from=backend /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf.template

ENV SPRING_PROFILES_ACTIVE=prod \
    PORT=80 \
    JAVA_OPTS="-Xms512m -Xmx1500m -XX:+UseZGC"

EXPOSE 80

CMD ["sh","-c","envsubst '${PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;' & exec java -Dserver.port=8080 ${JAVA_OPTS} -jar app.jar"]

# ============================================================
# 预构建版本 (若 Render 内 mvn 超时/OOM, 改用此段替换上面"阶段1+阶段2")
# 本地(WSL/Ubuntu)先出包:
#   cd backend && mvn -pl ruoyi-admin -am clean package -DskipTests
#   cd ../frontend && npm install && npm run build:prod
# 然后把下面这段替换掉上面"阶段1 + 阶段2"的全部内容:
# FROM eclipse-temurin:21-jre
# RUN apt-get update && apt-get install -y --no-install-recommends nginx gettext && rm -rf /var/lib/apt/lists/*
# WORKDIR /app
# COPY frontend/dist /usr/share/nginx/html
# COPY backend/ruoyi-admin/target/ruoyi-admin.jar app.jar
# COPY nginx.conf /etc/nginx/nginx.conf.template
# ENV SPRING_PROFILES_ACTIVE=prod PORT=80 JAVA_OPTS="-Xms512m -Xmx1500m -XX:+UseZGC"
# EXPOSE 80
# CMD ["sh","-c","envsubst '${PORT}' < /etc/nginx/nginx.conf.template > /etc/nginx/nginx.conf && nginx -g 'daemon off;' & exec java -Dserver.port=8080 ${JAVA_OPTS} -jar app.jar"]
# ============================================================
