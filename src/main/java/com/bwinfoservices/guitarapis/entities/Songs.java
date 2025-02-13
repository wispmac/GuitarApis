package com.bwinfoservices.guitarapis.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "songs")
public class Songs {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "songNum")
    private String songNum;

    @Column(name = "songTitle")
    private String songTitle;

    @Column(name = "dtLearnt")
    private String dtLearnt;

    @Column(name = "albumId")
    private Integer albumId;

    @Column(name = "composerId")
    private Integer composerId;

    @Column(name = "lyricistId")
    private Integer lyricistId;

    @Column(name = "capoInFret")
    private Integer capoInFret;
}
