package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "chordsUsed")
public class ChordsUsed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "songId")
    private Integer songId;

    @Column(name = "chordId")
    private Integer chordId;
}
