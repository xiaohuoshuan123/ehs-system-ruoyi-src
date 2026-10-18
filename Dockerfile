# 使用Node镜像编译前端
FROM node:20-alpine AS frontend
WORKDIR /build
COPY ruoyi-plus-ui/package*.json ./
RUN npm install -g pnpm@10
RUN pnpm install --no-frozen-lockfile
COPY ruoyi-plus-ui/. .
RUN pnpm run build

# 使用Java镜像编译后端
FROM eclipse-temurin:17-jdk-alpine AS backend
WORKDIR /build
RUN apk add --no-cache maven
COPY ruoyi-vue-plus/pom.xml .
RUN mvn dependency:go-offline -B
COPY ruoyi-vue-plus/. .
RUN mvn clean package -DskipTests

# 最终运行镜像
FROM eclipse-temurin:17-jdk-alpine
RUN apk add --no-cache nginx

# 复制前端构建产物
COPY --from=frontend /build/dist /usr/share/nginx/html

# 复制后端JAR
COPY --from=backend /build/ruoyi-admin/target/ruoyi-admin.jar /app.jar

# 复制nginx配置
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["sh", "-c", "nginx -g 'daemon off;' & java -jar /app.jar"]
