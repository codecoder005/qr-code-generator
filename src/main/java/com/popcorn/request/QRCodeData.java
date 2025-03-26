package com.popcorn.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QRCodeData {
    private String name;
    private String email;
    private String phoneNumber;
}
