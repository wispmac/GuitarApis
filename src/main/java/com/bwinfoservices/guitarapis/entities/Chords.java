package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chords")
public class Chords {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chordName")
    private String chordName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chords chords = (Chords) o;
        return Objects.equals(chordName, chords.chordName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chordName);
    }

    @Override
    public String toString() {
        return "Chords{" +
                "id=" + id +
                ", chordName='" + chordName + '\'' +
                '}';
    }
}
