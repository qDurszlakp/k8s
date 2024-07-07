echo [1/4] Build the image
cd ..
mvn -q clean package
docker build -q -t k8s .

echo [2/4] Remove running containers
docker rm -f k8s_demo

echo [3/4] Run containers
docker-compose up --build -d --quiet-pull

echo [4/4] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi
