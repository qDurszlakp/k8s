#!/bin/bash

# Set default value for skipping tests
SKIP_TESTS=true

# Check if the user provided an argument for skipping tests
if [ "$1" == "--run-tests" ]; then
    SKIP_TESTS=false
fi

echo [1/4] Build images

cd ..
if [ "$SKIP_TESTS" == "true" ]; then
    mvn -q clean package -DskipTests
else
    mvn -q clean package
fi

cd App
docker build -q -t app .
cd ..

cd Host
docker build -q -t host .
cd ..

echo [2/4] Kill containers
docker rm -f database

echo [3/4] Run containers
cd build
docker-compose up --build -d --quiet-pull

echo [4/4] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi