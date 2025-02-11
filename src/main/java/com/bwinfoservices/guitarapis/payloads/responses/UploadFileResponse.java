package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadFileResponse {
    private String status;
    private Integer id;
}
