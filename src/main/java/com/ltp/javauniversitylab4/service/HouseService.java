package com.ltp.javauniversitylab4.service;

import com.ltp.javauniversitylab4.dto.request.HouseRequestDto;
import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.model.House;
import com.ltp.javauniversitylab4.repository.HotelRepository;
import com.ltp.javauniversitylab4.repository.HouseRepository;
import com.ltp.javauniversitylab4.utils.HouseCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public void addOrUpdateHouse(HouseRequestDto houseDto) {
        HouseCondition houseCondition = houseDto.getHouseCondition();
        int personInHouse = houseDto.getPersonInHouse();
        Hotel hotel = hotelRepository.findById(houseDto.getHotelId()).orElse(null);

        House house = new House(houseCondition, personInHouse, hotel);
        houseRepository.save(house);
    }

    public List<House> findAllHouses() {
        return houseRepository.findAll();
    }

    public House findHouseById(Long id) {
        return houseRepository.findById(id).orElse(new House(-1L));
    }

    public void updateHouse(House house){
        houseRepository.save(house);
    }

    public void deleteHouseById(Long id) {
        houseRepository.deleteById(id);
    }



}
