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
    echo "--push-images       Push images to registry (qwerty2137/server, qwerty2137/foo)"
    exit 0;
fi

################## SCRIPT ##################

# Exit immediately if a command exits with a non-zero status.
set -e
# Treat pipe errors
set -o pipefail

# Set default value for skipping tests
SKIP_TESTS=true
SKIP_IMAGES_PUSH=true

# Check if the user provided an argument for running tests
for arg in "$@"; do
    if [ "$arg" == "--run-tests" ]; then
        SKIP_TESTS=false
        break
    fi
done

# Check if the user provided an argument for docker push
for arg in "$@"; do
    if [ "$arg" == "--push-images" ]; then
        SKIP_IMAGES_PUSH=false
        break
    fi
done

cecho [1/4] Build project and images
cd ..
if [ "$SKIP_TESTS" == "true" ]; then
    mvn -q clean package -DskipTests
else
    mvn -q clean package
fi

cd Server
docker build -q -t server .
cd ..

cd Foo
docker build -q -t foo .
cd ..

cecho [2/4] Build tags and push images to repository

if [ "$SKIP_IMAGES_PUSH" == "false" ]; then
    DOCKER_REGISTRY="qwerty2137"
    docker tag server ${DOCKER_REGISTRY}/server:latest
    docker tag foo ${DOCKER_REGISTRY}/foo:latest
    docker push -q ${DOCKER_REGISTRY}/server:latest
    docker push -q ${DOCKER_REGISTRY}/foo:latest
else
    cecho "  Skipping image push."
fi

cecho [3/4] Ensure clean environment and run containers 
cd build
cecho "  Stopping and removing existing docker-compose environment ..."
docker-compose down --remove-orphans -v > /dev/null 2>&1
cecho "  Starting new containers ..."
docker-compose up --build -d --quiet-pull > /dev/null 2>&1

cecho [4/4] Remove dangling images
docker images -f dangling=true -q | xargs docker rmi