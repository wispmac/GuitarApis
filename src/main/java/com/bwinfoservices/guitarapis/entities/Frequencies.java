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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequencies that = (Frequencies) o;
        return Objects.equals(stringNum, that.stringNum) && Objects.equals(fretNum, that.fretNum) && Objects.equals(noteId, that.noteId) && Objects.equals(frequency, that.frequency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringNum, fretNum, noteId, frequency);
    }

    @Override
    public String toString() {
        return "Frequencies{" +
                "id=" + id +
                ", stringNum=" + stringNum +
                ", fretNum=" + fretNum +
                ", noteId=" + noteId +
                ", frequency=" + frequency +
                '}';
    }
}
