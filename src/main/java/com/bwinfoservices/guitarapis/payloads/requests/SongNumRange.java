package com.bwinfoservices.guitarapis.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongNumRange {
    private String songNumFrom;
    private String songNumTo;
}
