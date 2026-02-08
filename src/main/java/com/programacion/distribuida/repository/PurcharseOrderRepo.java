package com.programacion.distribuida.repository;

import com.programacion.distribuida.model.PurcharseOrder;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PurcharseOrderRepo extends JpaRepository<PurcharseOrder, Long> {
}
