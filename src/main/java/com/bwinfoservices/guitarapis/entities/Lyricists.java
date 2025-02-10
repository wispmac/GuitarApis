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
@Table(name = "lyricists")
public class Lyricists {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lyricistName")
    private String lyricistName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lyricists lyricists = (Lyricists) o;
        return Objects.equals(lyricistName, lyricists.lyricistName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lyricistName);
    }

    @Override
    public String toString() {
        return "Lyricists{" +
                "id=" + id +
                ", lyricistName='" + lyricistName + '\'' +
                '}';
    }
}
