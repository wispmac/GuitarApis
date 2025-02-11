package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StringListResponse {
    private String status;
    private List<String> lstData;
}
