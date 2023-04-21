package ru.socks.testtaskforstartingemployment.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.socks.testtaskforstartingemployment.dto.SockDto;
import ru.socks.testtaskforstartingemployment.service.SockService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SockController {
    private final Logger logger = LoggerFactory.getLogger(SockController.class);
    private final SockService sockService;

    @PostMapping("/income")
    public ResponseEntity<SockDto> addSock(@Valid @RequestBody SockDto sockDto) {
        logger.info("method is - addSock");
        sockService.addSock(sockDto);
        if (sockDto.getQuantity() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(sockDto);
    }

    @PostMapping("/outcome")
    public ResponseEntity<SockDto> subtractSock(@Valid @RequestBody SockDto sockDto) {
        logger.info("method is - subtractSock");
        sockService.subtractSock(sockDto);
        return ResponseEntity.ok(sockDto);
    }

    @GetMapping
    public String getSock(String color, String operation, Integer cottonPart) {
        logger.info("method is - getSock");
        return sockService.getSock(color, operation, cottonPart);
    }

}
