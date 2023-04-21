package ru.socks.testtaskforstartingemployment.dto;

import lombok.Data;

@Data
public class GettingTheBalanceDto {

    private Long id;
    private String color;
    private int cottonPart;

    public GettingTheBalanceDto(Long id, String color, int cottonPart) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
    }
}
