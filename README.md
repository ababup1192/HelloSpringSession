# HelloSpringSession

## Build & Push Docker Image

```
$ docker login
$ ./gradlew build buildDocker
```

## Launch App in Docker

```
$ cd [Your WorkSpace]
$ git clone https://gist.github.com/ababup1192/97260bf3dcfb084b59975d4aca17a1f3 spring-docker
$ cd spring-docker
$ docker-compose up -d
// Launch Application on ${docker ip}:8080
```