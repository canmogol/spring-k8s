# Spring Boot Kubernetes

SpringBoot with K8S ConfigMap example.

## Compile

Here are the steps to compile the Java application and create the docker image. Please see the "bootstrap.yaml" configuration file for K8S integration.

**How to compile Java application**
```bash
mvn clean install
```

**How to create Java docker image**
```bash
docker build -t spring-k8s .
```

## Deploy

Tag and push the docker image to remote repository.

```bash
# tag the docker image with the remote repository name.
docker tag spring-k8s:latest canmogol/spring-k8s:0.0.1

# push the docker image to remote repository.
# you need to login to the remote repository before pushing the image.
docker push canmogol/spring-k8s:0.0.1
```

Apply the K8S configuration files.

```bash
# apply the ConfigMap; creates the 'spring-k8s-config' configuration.
kubectl apply -f k8s/configmap.yaml

# apply the Application; creates the service, deployment and the pod.
kubectl apply -f k8s/deployment-configmap.yaml
```

## Config Changes

You can send a request to this service from other services using the http://spring-k8s-service/ endpoint to get the latest configuration changes.
Otherwise, you can port forward the service to your local machine and send a request to the http://localhost:8080/ endpoint to get the latest configuration changes.

```bash 
# on one terminal, port forward the service to your local machine.
kubectl port-forward service/spring-k8s-service 8080:80

# on another terminal, send a request to the http://localhost:8080/ endpoint to get the latest configuration changes.
curl -v http://localhost:8080/
# should return something similar to the following, since the ConfigMap content replaced the default values.
# {"key1":"kubernetes-value1","key2":"kubernetes-value2"}
```

When we change the data in the 'spring-k8s-config' ConfigMap, we can restart the application 
and it will get the latest configuration changes. 
```bash
# edit the 'k8s/configmap.yaml' file and change the data.
# and then apply the ConfigMap again.
kubectl apply -f k8s/configmap.yaml

# or, alternatively, 
# you can directly edit the 'spring-k8s-config' ConfigMap and change the data.
kubectl edit configmap/spring-k8s-config

# restart the application.
kubectl rollout restart deployment/spring-k8s
```
