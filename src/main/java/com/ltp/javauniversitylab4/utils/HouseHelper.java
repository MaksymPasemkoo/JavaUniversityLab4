package com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.request.HouseRequestDto;
import com.ltp.javauniversitylab4.dto.response.HouseResponseDto;
import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.model.House;

import java.util.List;

public class HouseHelper {
    public static HouseResponseDto convertToResponseDto(House house) {
        Long id = house.getHouseId();
        HouseCondition houseCondition = house.getHouseCondition();
        int personInHouse = house.getPersonInHouse();
        List<HouseAmenitie> houseAmenities = house.getHouseAmenities();
        Hotel hotel = house.getHotel();

        return new HouseResponseDto(id, houseCondition, personInHouse, houseAmenities, hotel);
    }

    public static HouseRequestDto convertToRequestDto(House house) {
        Long houseId = house.getHouseId();
        HouseCondition houseCondition = house.getHouseCondition();
        int personInHouse = house.getPersonInHouse();

        return new HouseRequestDto( houseId,houseCondition,personInHouse);
    }

    public static double priceOfHouse(final House house) {
        return house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenitie::getPrice)
                .sum();
    }

    public static double expenseOfHouse(final House house) {
        return house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenitie::getExpense)
                .sum();
    }
}
