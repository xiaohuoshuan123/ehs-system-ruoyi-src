#!/bin/bash
set -e

echo "Starting application..."
echo "Spring Profile: $SPRING_PROFILES_ACTIVE"
echo "DB_HOST: ${DB_HOST:-not set}"
echo "REDIS_HOST: ${REDIS_HOST:-not set}"

# 先启动 Java 后端（前台运行）
exec java -Dserver.port=8080 -Xms128m -Xmx384m -XX:+UseSerialGC \
  -jar /app/app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} &

# 等待 Java 启动
JAVA_PID=$!
echo "Java PID: $JAVA_PID"

# 启动 nginx（后台运行）
nginx -g 'daemon off;' &
NGINX_PID=$!
echo "Nginx PID: $NGINX_PID"

# 监控两个进程
wait $JAVA_PID || echo "Java exited with error"
wait $NGINX_PID || echo "Nginx exited with error"
