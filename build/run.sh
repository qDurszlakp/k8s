echo [1/3] Build images

cd ..
mvn -q clean package

cd App
docker build -q -t app .
cd ..

cd Host
docker build -q -t host .
cd ..

echo [2/3] Run containers
cd build
docker-compose up --build -d --quiet-pull

echo [3/3] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi
