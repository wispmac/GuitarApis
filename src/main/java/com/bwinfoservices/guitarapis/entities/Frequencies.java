package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "frequencies")
public class Frequencies {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stringNum")
    private Integer stringNum;

    @Column(name = "fretNum")
    private Integer fretNum;

    @Column(name = "noteId")
    private Integer noteId;

    @Column(name = "frequency")
    private Integer frequency;
}
