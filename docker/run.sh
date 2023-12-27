docker run -p 8080:8080 \
-p 5005:5005 \
-e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" \
-d \
--rm \
--name k8s_demo \
-v volume:/app/data \
k8s:latest