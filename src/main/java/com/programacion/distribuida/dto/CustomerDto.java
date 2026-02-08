package com.programacion.distribuida.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private Integer version;

    private List<PurcharseOrderDto> purchaseOrders;

}
