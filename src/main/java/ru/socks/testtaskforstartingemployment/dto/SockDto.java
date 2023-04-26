package ru.socks.testtaskforstartingemployment.dto;

import lombok.Data;

@Data
public class SockDto {

    private Long id;
    private String color;
    private int cottonPart;
    private int quantity;

    public SockDto(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
