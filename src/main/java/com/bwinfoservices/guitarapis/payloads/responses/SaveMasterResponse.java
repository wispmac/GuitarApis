package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveMasterResponse {
    private String status;
    private Integer id;
}
