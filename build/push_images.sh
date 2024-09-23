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

################## SCRIPT ##################

cecho [1/2] Build images
cd ..
mvn -q clean package -DskipTests

cd App
docker build -q -t app .
cd ..

cd Host
docker build -q -t host .
cd ..

cecho [2/2] Build tags and push images to repository

docker tag app qwerty2137/app:latest
docker tag host qwerty2137/host:latest
docker push -q qwerty2137/app:latest
docker push -q qwerty2137/host:latest
