package com.programacion.distribuida.config;

import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.discovery.consul.ConsulConfiguration;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ConsulTagsCustomizer implements BeanCreatedEventListener<ConsulConfiguration> {

    @Override
    public ConsulConfiguration onCreated(BeanCreatedEvent<ConsulConfiguration> event) {
        ConsulConfiguration config = event.getBean();

        List<String> traefikTags = new ArrayList<>();
        traefikTags.add("traefik.enable=true");
        traefikTags.add("traefik.http.routers.customers.rule=PathPrefix(`/app-customers`)");
        traefikTags.add("traefik.http.routers.customers.priority=10");
        traefikTags.add("traefik.http.middlewares.customers-stripprefix.stripPrefix.prefixes=/app-customers");
        traefikTags.add("traefik.http.routers.customers.middlewares=customers-stripprefix");

        config.getRegistration().setTags(traefikTags);

        return config;
    }
}