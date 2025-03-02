package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IntegerListResponse {
    private String status;

    private Integer count;

    private List<Integer> lstData;
}
