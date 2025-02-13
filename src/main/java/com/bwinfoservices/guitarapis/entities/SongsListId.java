package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class SongsListId implements Serializable {
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "songNum")
    private String songNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SongsListId entity = (SongsListId) o;
        return Objects.equals(this.songNum, entity.songNum) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songNum, id);
    }
}