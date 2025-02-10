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
@Table(name = "composers")
public class Composers {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "composerName")
    private String composerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Composers composers = (Composers) o;
        return Objects.equals(composerName, composers.composerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(composerName);
    }

    @Override
    public String toString() {
        return "Composers{" +
                "id=" + id +
                ", composerName='" + composerName + '\'' +
                '}';
    }
}
