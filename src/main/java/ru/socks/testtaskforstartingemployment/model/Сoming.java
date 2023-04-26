package ru.socks.testtaskforstartingemployment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity  // A special class whose objects are saved to the database
@Table(name = "coming")
@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Сoming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "quantity")
    private int quantity; // the number of pairs of socks, an integer greater than 0

    @OneToOne
    @JoinColumn(name = "sock_id")
    Sock sock;

}
