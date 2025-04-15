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

cd Server
docker build -q -t server .
cd ..

cd Foo
docker build -q -t foo .
cd ..

cecho [2/2] Build tags and push images to repository

docker tag server qwerty2137/server:latest
docker tag foo qwerty2137/foo:latest
docker push -q qwerty2137/server:latest
docker push -q qwerty2137/foo:latest
