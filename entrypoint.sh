#!/bin/bash
set -e

echo "=== EHS System Starting ==="
echo "Profile: ${SPRING_PROFILES_ACTIVE:-prod}"
echo "DB_HOST: ${DB_HOST:-not set}"
echo "REDIS_HOST: ${REDIS_HOST:-not set}"
echo ""

# 启动 Java 后端（前台运行，捕获退出码）
echo "Starting Java backend on port 8080..."
java -Dserver.port=8080      -Xms128m -Xmx384m      -XX:+UseSerialGC      -jar /app/app.jar      --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-prod} &

JAVA_PID=$!
echo "Java PID: $JAVA_PID"

# 启动 nginx（前台运行）
echo "Starting nginx..."
nginx -g 'daemon off;' &
NGINX_PID=$!
echo "Nginx PID: $NGINX_PID"

# 等待 Java 进程
wait $JAVA_PID
JAVA_EXIT=$?
echo "Java exited with code: $JAVA_EXIT"

# 如果 Java 退出，也退出 nginx
kill $NGINX_PID 2>/dev/null || true
exit $JAVA_EXIT
