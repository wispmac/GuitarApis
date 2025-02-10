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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChordFormation that = (ChordFormation) o;
        return Objects.equals(chordId, that.chordId) && Objects.equals(noteId, that.noteId) && Objects.equals(stringNum, that.stringNum) && Objects.equals(fretNum, that.fretNum) && Objects.equals(fingerNum, that.fingerNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chordId, noteId, stringNum, fretNum, fingerNum);
    }

    @Override
    public String toString() {
        return "ChordFormation{" +
                "id=" + id +
                ", chordId=" + chordId +
                ", noteId=" + noteId +
                ", stringNum=" + stringNum +
                ", fretNum=" + fretNum +
                ", fingerNum=" + fingerNum +
                '}';
    }
}
