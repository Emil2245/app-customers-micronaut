package com.programacion.distribuida.repository;


import com.programacion.distribuida.model.Customer;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;


@Repository
public interface CustomersRepository extends JpaRepository<Customer, Long> {
}
