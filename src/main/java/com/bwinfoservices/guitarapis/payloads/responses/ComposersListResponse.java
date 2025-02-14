package com.bwinfoservices.guitarapis.payloads.responses;

import com.bwinfoservices.guitarapis.entities.Composers;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComposersListResponse {
    private String status;

    private Integer count;

    private List<Composers> composerList;
}
