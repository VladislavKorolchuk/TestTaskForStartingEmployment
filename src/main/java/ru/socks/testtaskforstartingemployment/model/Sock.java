package ru.socks.testtaskforstartingemployment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity  // A special class whose objects are saved to the database
@Table(name = "sock")
@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "color")
    private String color;      // socks color
    @Column(name = "cotton_part")
    private int cottonPart;    // percentage of cotton in socks, integer from 0 to 100

}
