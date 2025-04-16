[![Integration Tests](https://github.com/qDurszlakp/fen/actions/workflows/maven.yml/badge.svg)](https://github.com/qDurszlakp/k8s/actions/workflows/maven.yml)

````
Hey you! This is a playground for training purposes.

Stack: 
Java
Spring Boot
PostgresSql
Terraform
Docker
k8s
````
**Commands**
````
./run_with_docker.sh    - Build and run the project on local docker server.
./push_images.sh        - Build and push images to docker registry.
./run_with_k8s.sh       - Build and run the project on local k8s cluster.
````

```
k8s:
terraform apply -var 'registry_password=<YOUR_DOCKER_HUB_PASSWORD>' -auto-approve
```