package com.bwinfoservices.guitarapis.payloads.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileResponse {
    private String status;

    private String fileName;

    private Long fileSize;

    private byte[] data;
}
