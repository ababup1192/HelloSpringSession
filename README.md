# HelloSpringSession

## Build & Push Docker Image

```
$ docker login
$ ./gradlew build buildDocker
```

## Launch App in Docker

```
$ cd [Your WorkSpace]
$ git clone https://gist.github.com/97260bf3dcfb084b59975d4aca17a1f3.git spring-docker
$ cd spring-docker
$ docker-compose -d up web
// Launch Application on ${docker ip}:8080
```
