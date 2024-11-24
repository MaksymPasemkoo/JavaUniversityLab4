package com.ltp.javauniversitylab4.utils;

import com.ltp.javauniversitylab4.dto.request.HouseRequestDto;
import com.ltp.javauniversitylab4.dto.response.HouseResponseDto;
import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.model.House;
import java.time.Month;
import java.util.List;

public class HouseHelper {
    public static HouseResponseDto convertToResponseDto(final House house) {
        final Long id = house.getHouseId();
        final HouseCondition houseCondition = house.getHouseCondition();
        final int personInHouse = house.getPersonInHouse();
        final List<HouseAmenitie> houseAmenities = house.getHouseAmenities();
        final Hotel hotel = house.getHotel();

        return new HouseResponseDto(id, houseCondition, personInHouse, houseAmenities, hotel);
    }

    public static HouseRequestDto convertToRequestDto(final House house) {
        final Long houseId = house.getHouseId();
        final HouseCondition houseCondition = house.getHouseCondition();
        final int personInHouse = house.getPersonInHouse();

        return new HouseRequestDto( houseId,houseCondition,personInHouse);
    }

    public static double priceOfHouse(final House house,final Month month) {
        final double price = house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenitie::getPrice)
                .sum();
        final double discount = 0.8;

        if (month == Month.NOVEMBER || month == Month.MARCH) {
            return price * discount;
        }
        return price;
    }

    public static double expenseOfHouse(final House house,final Month month) {
        final double expense = house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenitie::getExpense)
                .sum();
        final double discount = 0.8;

        if (month == Month.NOVEMBER || month == Month.MARCH) {
            return expense * discount;
        }
        return expense;
    }
}
