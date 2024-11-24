package com.ltp.javauniversitylab4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {
    private Long clientId;
    private String name;
    private double balance;
}
