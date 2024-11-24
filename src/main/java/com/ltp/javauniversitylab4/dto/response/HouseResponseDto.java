package com.ltp.javauniversitylab4.dto.response;

import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.utils.HouseAmenitie;
import com.ltp.javauniversitylab4.utils.HouseCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDto {
    private Long houseId;
    private HouseCondition houseCondition;
    private int personInHouse;
    private List<HouseAmenitie> houseAmenities;
    private Hotel hotel;
}
