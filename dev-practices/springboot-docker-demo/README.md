
## Introduction

A simple load balancing implementation using Docker + Nginx + SpringBoot.


## Run this project

1. build images needed for this project first.

```shell script
docker build -t springboot-docker-demo/demo1 ./demo1

docker build -t springboot-docker-demo/demo2 ./demo2

# or build these images using dockerfile-maven-plugin

mvn clean compiler:compile jar:jar spring-boot:repackage dockerfile:build clean 
```

2. then using docker-compose [recommended] (or docker stack deploy ) the containers.

```shell script
docker-compose up -d
```

## Result Testing

![](./images/sb-load-balancing-result-testing.gif)
