package com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.response.ReserveResponseDto;
import com.ltp.javauniversitylab4.model.Reserve;
import java.time.LocalDate;

public class ReserveHelper {
    public static ReserveResponseDto convertToResponseDto(final Reserve reserve) {
        final Long reserveId = reserve.getReserveId();
        final Long houseId = reserve.getHouse().getHouseId();
        final Long clientId = reserve.getClient().getClientId();
        final LocalDate date = reserve.getDate();
        return new ReserveResponseDto(reserveId, houseId, clientId, date);
    }


}
