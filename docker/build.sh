cd ..
mvn clean package
docker build -t k8s .
docker images
