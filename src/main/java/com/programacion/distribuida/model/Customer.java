package com.programacion.distribuida.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    private Long id;

    private String name;
    private String email;
    private Integer version;

    @OneToMany(mappedBy = "customer")
    private List<PurcharseOrder> purcharseOrders = new ArrayList<>();

}
