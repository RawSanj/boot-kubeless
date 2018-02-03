# boot-kubeless
## Spring Boot project for [Kubeless] Custom [Runtime]

A simple Spring Boot Application to demonstrate how to build and run Custom Java Runtime on [Kubeless] for HTTP and Kafka Triggers. 

It has two applications:
1. **boot-kubeless-http**: A simple Spring Boot app for Kubeless HTTP trigger. It provides a **GET** and **POST** endpoint to add/list simple domain entity to demonstrate the use of various Spring projects. 
1. **boot-kubeless-kafka**: A simple Spring Boot app as Kafka Consumer for Kubeless Topic/Kafka trigger. 

### Tech

**boot-kubeless** uses a number of open source projects:

* [Spring Boot] - An opinionated framework for building production-ready Spring applications. It favors convention over configuration and is designed to get you up and running as quickly as possible.
* [Kubeless] - The Kubernetes Native Serverless Framework. 
* [Spring Data JPA] - Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories.
* [Spring Boot Actuator] - Actuator endpoints allow you to monitor and interact with your application.
* [Spring for Kafka] - The Spring for Apache Kafka project applies core Spring concepts to the development of Kafka-based messaging solutions.

### Installation and Configuration

```sh
$ git clone https://github.com/RawSanj/boot-kubeless.git

$ cd boot-kubeless
```

#### Build and Run the applications:

Build and run the **boot-kubeless-http** application:
```sh
$ cd boot-kubeless-http

$ mvn clean package

$ mvn spring-boot:run
```

Build and run the **boot-kubeless-kafka** application:

**Note:** Download and Install [Kafka]. Set the Kafka Server Address and Topic name in `application.yml` file.  
```sh
$ cd boot-kubeless-kafka

$ mvn clean package

$ mvn spring-boot:run
```


### Run in Docker

#### Build and run the *boot-kubeless-http* locally in Docker:

Build the JAR file:
```sh
$ cd boot-kubeless-http

$ mvn clean package
```

Build docker image:
```sh
$ mvn docker:build
```

Run docker image:
```sh
$ docker run -d -p 8080:8080 \
$ rawsanj/boot-kubeless-http
```

#### Build and run the *boot-kubeless-kafka* locally in Docker:

Build the JAR file:
```sh
$ cd boot-kubeless-kafka
$ mvn clean package
```

Build docker image:
```sh
$ mvn docker:build
```

You would need to run Kafka in Docker. Try this [image].
Run docker image:
```sh
$ docker run -d -p 8080:8080 \
$ rawsanj/boot-kubeless-kafka --link <Kafka_Container>:alias
```

### Deploy on Kubernetes as FaaS using kubeless cli

#### Setup and Install Kubeless. 
Follow the Steps [here].

#### Deploy the *boot-kubeless-http* as Kubeless Function with HTTP Trigger
Deploy the Spring Boot app *boot-kubeless-http* as a Function using docker image available in Docker Hub:

```sh
$ kubeless function deploy --runtime-image rawsanj/boot-kubeless-http --trigger-http boot-kubeless-http
```

#### Test the function: 

Calls the **GET** method of Spring Boot App
```sh
$ kubeless function call boot-kubeless-http
```

Calls the **POST** method of Spring Boot App
```sh
$ kubeless function call boot-kubeless-http --data '{"language":"Go","rate":1}'
```


#### Deploy the *boot-kubeless-kafka* as Kubeless Function with Kafka/Topic Trigger

Before deploying the function, lets create kafka topic:
```sh
$ kubeless topic create boot-topic
```

Deploy the Spring Boot app *boot-kubeless-kafka* as a Function using docker image available in Docker Hub:

```sh
$ kubeless function deploy boot-kubeless-kafka --runtime-image rawsanj/boot-kubeless-kafka --trigger-topic boot-topic --env KUBELESS_KAFKA_SVC=kafka --env  KUBELESS_KAFKA_NAMESPACE=kubeless
```
**Note:** If you have your own Kafka Cluster running on K8S, update the namespace and service name in the environment variables above.


#### Test the function: 

Publish message onto the Kafka topic
```sh
$ kubeless topic publish --topic boot-topic --data "Hello From Kafka"
```

Ssh into **boot-kubeless-kafka** Pods to check the Messages getting consumed.
```sh
$ kubectl get pods | grep boot-kubeless-kafka   // Note the Pod name.

$ kubectl logs boot-kubeless-kafka-68b.... // Above pod name.
```


#### You can also test the Function from [Kubeless UI].


License
----

Apache License 2.0

Copyright (c) 2018 Sanjay Rawat

[//]: #

   [Spring Boot]:<https://projects.spring.io/spring-boot/>
   [Kubeless]: <http://kubeless.io/>
   [Runtime]: <https://github.com/kubeless/kubeless/blob/master/docs/runtimes.md#custom-runtime-alpha>
   [Spring Data JPA]: <https://projects.spring.io/spring-data-jpa/>
   [Spring Boot Actuator]: <https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html>
   [Spring for Kafka]: <https://projects.spring.io/spring-kafka/>
   [here]: <https://github.com/kubeless/kubeless#installation>
   [Kubeless UI]: <https://github.com/kubeless/kubeless-ui>
   [Kafka]: <https://kafka.apache.org/quickstart>
   [image]: <https://hub.docker.com/r/spotify/kafka>
