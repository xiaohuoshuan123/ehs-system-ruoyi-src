# 阶段1: 编译后端
FROM eclipse-temurin:17-jdk-alpine AS backend
WORKDIR /build
RUN apk add --no-cache maven
COPY backend/pom.xml .
RUN mvn dependency:go-offline -B
COPY backend/. .
RUN mvn clean package -DskipTests

# 阶段2: 运行
FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache nginx

WORKDIR /app

# 前端已预先构建并提交到仓库，直接复制
COPY frontend/dist /usr/share/nginx/html
COPY --from=backend /build/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["sh", "-c", "nginx -g 'daemon off;' & java -jar app.jar"]
