package ru.socks.testtaskforstartingemployment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.socks.testtaskforstartingemployment.model.Sock;
import ru.socks.testtaskforstartingemployment.model.Сoming;

import java.util.List;

public interface СomingRepository extends JpaRepository<Сoming, Long> {
    List<Сoming> findAllById(long id);

}
