# employee-docker
This project will help to study docker container

## Docker

### JDK
building docker image 
```shell
docker build -f Docker/jdk.Dockerfile -t employee:jdk .
```
start the application
```shell
docker run -it -ePORT:8080 -p8080:8080 employee:jdk .
```

## Ansible
running ansible to deploy 
```shell
ansible-playbook -i ansible/inventories/hosts ansible/deploy.yml
```



