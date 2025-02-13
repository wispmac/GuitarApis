package com.bwinfoservices.guitarapis.payloads.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadFileRequest {
    private Integer id;

    private Integer songId;

    private String fileName;

    private String fileType;

    private byte[] fileData;
}
