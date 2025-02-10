package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chordsList")
public class ChordsList {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "stringNum")
    private Integer stringNum;

    @Column(name = "fretNum")
    private Integer fretNum;

    @Column(name = "chordName")
    private String chordName;

    @Column(name = "noteName")
    private String noteName;

    @Column(name = "finger")
    private String finger;

    @Column(name = "frequency")
    private Integer frequency;
}
