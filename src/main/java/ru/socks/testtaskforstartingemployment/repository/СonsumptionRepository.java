package ru.socks.testtaskforstartingemployment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.socks.testtaskforstartingemployment.model.Сoming;
import ru.socks.testtaskforstartingemployment.model.Сonsumption;

import java.util.List;

public interface СonsumptionRepository extends JpaRepository<Сonsumption, Long> {
    List<Сonsumption> findAllById(long id);
}
