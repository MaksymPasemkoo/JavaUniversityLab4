package com.ltp.javauniversitylab4.dto.request;

import com.ltp.javauniversitylab4.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRequestDto {
    private Long houseId;
    private Long clientId;
    private String date;

    public LocalDate getDate() {
        return DateUtil.dateFormatter(date);
    }
}
