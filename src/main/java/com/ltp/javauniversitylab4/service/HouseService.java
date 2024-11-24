package com.ltp.javauniversitylab4.service;

import com.ltp.javauniversitylab4.dto.request.HouseRequestDto;
import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.model.House;
import com.ltp.javauniversitylab4.repository.HotelRepository;
import com.ltp.javauniversitylab4.repository.HouseRepository;
import com.ltp.javauniversitylab4.utils.HouseCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HouseService {
    @Autowired
    private final HouseRepository houseRepository;
    @Autowired
    private final HotelRepository hotelRepository;

    public HouseService(final HouseRepository houseRepository, final HotelRepository hotelRepository) {
        this.houseRepository = houseRepository;
        this.hotelRepository = hotelRepository;
    }

    public void addOrUpdateHouse(final HouseRequestDto houseDto) {
        final HouseCondition houseCondition = houseDto.getHouseCondition();
        final int personInHouse = houseDto.getPersonInHouse();
        final Hotel hotel = hotelRepository.findById(houseDto.getHotelId()).orElse(null);

        final House house = new House(houseCondition, personInHouse, hotel);
        houseRepository.save(house);
    }

    public List<House> findAllHouses() {
        return houseRepository.findAll();
    }

    public House findHouseById(final Long id) {
        return houseRepository.findById(id).orElse(new House(-1L));
    }

    public void updateHouse(final House house) {
        houseRepository.save(house);
    }

    public void deleteHouseById(final Long id) {
        houseRepository.deleteById(id);
    }


}
