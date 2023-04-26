package ru.socks.testtaskforstartingemployment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.socks.testtaskforstartingemployment.model.Sock;

import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Long> {

    Optional<Sock> findByColorAndCottonPart(String color, Integer cottonPart);
    Optional<Sock> findByColor(String color);

}
