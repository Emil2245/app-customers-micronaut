package com.programacion.distribuida.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@Data
public class PurcharseOrderDto {
    private Long id;
    private LocalDateTime placedOn;
    private LocalDateTime deliveredOn;
    private Integer status;
    private Integer total;

}
