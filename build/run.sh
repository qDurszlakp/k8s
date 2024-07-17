echo [1/4] Build images

cd ..
mvn -q clean package

cd App
docker build -q -t app .
cd ..

cd Host
docker build -q -t host .
cd ..

#echo [2/4] Remove running containers
#docker rm -f k8s_demo

echo [3/4] Run containers
cd build
docker-compose up --build -d --quiet-pull

echo [4/4] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi
