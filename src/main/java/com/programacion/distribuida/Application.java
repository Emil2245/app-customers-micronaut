package com.programacion.distribuida;

import io.micronaut.runtime.Micronaut;
@KubernetesApplication(
        name = "mi-servicio",
        replicas = 2,
        serviceType = ServiceType.ClusterIP,
        ports = @Port(name = "http", containerPort = 8080)
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
