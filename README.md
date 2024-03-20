# employee-docker
This project will help to study docker container

## JDK
building docker image 
```shell
docker build -f Docker/jdk.Dockerfile -t employee:jdk .
```
start the application
```shell
docker run -it -ePORT:8080 -p8080:8080 employee:jdk .
```
