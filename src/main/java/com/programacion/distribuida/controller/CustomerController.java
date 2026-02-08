package com.programacion.distribuida.controller;

import com.programacion.distribuida.dto.CustomerDto;
import com.programacion.distribuida.dto.PurcharseOrderDto;
import com.programacion.distribuida.repository.CustomersRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Controller("/customers")
public class CustomerController {

    @Inject
    private final CustomersRepository repo;

    @Get("/")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<?> findAll() {
        List<CustomerDto> allCustomers =
        repo.findAll().stream()
                .map(customer -> {
                    var purchaseOrders = customer.getPurcharseOrders().stream()
                            .map(po -> PurcharseOrderDto.builder()
                                    .id(po.getId())
                                    .placedOn(po.getPlacedOn())
                                    .deliveredOn(po.getDeliveredOn())
                                    .status(po.getStatus())
                                    .total(po.getTotal())
                                    .build()
                            )
                            .toList();

                    return CustomerDto.builder()
                            .id(customer.getId())
                            .name(customer.getName())
                            .email(customer.getEmail())
                            .version(customer.getVersion())
                            .purchaseOrders(purchaseOrders)
                            .build();
                })
                .toList();

        return HttpResponse.accepted().body(allCustomers);
    }
}


