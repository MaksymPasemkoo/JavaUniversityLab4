package com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.response.ReserveResponseDto;
import com.ltp.javauniversitylab4.model.Reserve;

import java.time.LocalDate;

public class ReserveHelper {
    public static ReserveResponseDto convertToResponseDto(Reserve reserve){
        Long reserveId = reserve.getReserveId();
        Long houseId = reserve.getHouse().getHouseId();
        Long clientId = reserve.getClient().getClientId();
        LocalDate date = reserve.getDate();
        return new ReserveResponseDto(reserveId,houseId,clientId,date);
    }


}
