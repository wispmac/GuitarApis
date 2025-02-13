package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.entities.Albums;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlbumsListResponse {
    private String status;

    private Integer count;

    private List<Albums> albumsList;
}
