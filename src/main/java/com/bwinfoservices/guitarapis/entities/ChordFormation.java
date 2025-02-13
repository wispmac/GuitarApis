package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "chordFormation")
public class ChordFormation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chordId")
    private Integer chordId;

    @Column(name = "noteId")
    private Integer noteId;

    @Column(name = "stringNum")
    private Integer stringNum;

    @Column(name = "fretNum")
    private Integer fretNum;

    @Column(name = "fingerNum")
    private Integer fingerNum;
}
