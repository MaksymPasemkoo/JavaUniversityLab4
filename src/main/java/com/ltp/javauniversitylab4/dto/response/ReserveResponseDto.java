package com.ltp.javauniversitylab4.dto.response;

import com.ltp.javauniversitylab4.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveResponseDto {
    private Long reserveId;
    private Long houseId;
    private Long clientId;
    private LocalDate date;
}
