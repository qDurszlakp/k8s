echo [1/4] Build the image
cd ..
mvn -q clean package
docker build -q -t k8s .

echo [2/4] Remove running containers
docker rm -f k8s_demo && \

echo [3/4] Run container
docker run -p 8080:8080 \
-p 5005:5005 \
-e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" \
-d \
--rm \
--name k8s_demo \
-v volume:/app/data \
k8s:latest

echo [4/4] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi