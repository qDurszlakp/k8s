#!/bin/bash

################## HELPERS ##################
function cecho() {
    # Default color is green
    local color=32

    # Check if a color code is provided as the first argument
    if [[ "$1" =~ ^[0-9]+$ ]]; then
        color=$1
        shift
    fi

    echo -e "\e[${color}m$*\e[0m"
}

if [[ "$1" == "--help" || "$1" == "-h" || "$1" == "-help" ]]; then
    echo "--run-tests         Run test while build"
    echo "--push-images       Push images to registry"
    exit 0;
fi

################## SCRIPT ##################

# Set default value for skipping tests
SKIP_TESTS=true
SKIP_IMAGES_PUSH=true

# Check if the user provided an argument for skipping tests
for arg in "$@"; do
    if [ "$arg" == "--run-tests" ]; then
        SKIP_TESTS=false
        break
    fi
done

# Check if the user provided an argument for skipping docker push
for arg in "$@"; do
    if [ "$arg" == "--push-images" ]; then
        SKIP_IMAGES_PUSH=false
        break
    fi
done

cecho [1/5] Build images
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

cecho [2/5] Build tags and push images to repository

if [ "$SKIP_IMAGES_PUSH" == "false" ]; then
    docker tag app qwerty2137/app:latest
    docker tag host qwerty2137/host:latest
    docker push -q qwerty2137/app:latest
    docker push -q qwerty2137/host:latest
else
    cecho skipped
fi

cecho [3/5] Kill containers
docker rm -f database

cecho [4/5] Run containers
cd build
docker-compose up --build -d --quiet-pull

cecho [5/5] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi


