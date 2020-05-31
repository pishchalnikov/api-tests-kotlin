# api-tests-kotlin

[![CircleCI](https://circleci.com/gh/pishchalnikov/api-tests-kotlin.svg?style=svg)](https://circleci.com/gh/pishchalnikov/api-tests-kotlin)

Examples for API Testing with Kotlin

Requirements:
* OpenJDK
* gradle

Build tests:
```bash
$ ./gradlew build
```

Run tests:
```bash
$ ./gradlew test
```

Run tests in Docker:
```bash
$ docker build --tag api-tests-kotlin .
$ docker run api-tests-kotlin
```
