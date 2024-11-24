package com.ltp.javauniversitylab4.utils;

import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public enum HotelAmenitie {
    FREE_WIFI("free Wi-Fi"),
    BREAKFAST("breakfast"),

    GYM("gym"),
    SWIMMING_POOL("swimming pool"),
    SPA_SERVICES("spa services"),
    RESTAURANT("restaurant"),
    CONCIERGE_SERVICE("concierge service"),
    PARKING("parking");

    private final String amenitieText;
    HotelAmenitie(final String amenitieText){
        this.amenitieText = amenitieText;
    }

    public static List<HotelAmenitie> getAmenities(){
        return Arrays.asList(HotelAmenitie.values());
    }
}
