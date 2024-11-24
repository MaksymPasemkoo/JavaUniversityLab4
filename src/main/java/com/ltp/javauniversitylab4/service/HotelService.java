package com.ltp.javauniversitylab4.service;

import com.ltp.javauniversitylab4.dto.request.HotelRequestDto;
import com.ltp.javauniversitylab4.model.Hotel;
import com.ltp.javauniversitylab4.model.House;
import com.ltp.javauniversitylab4.model.Reserve;
import com.ltp.javauniversitylab4.repository.HotelRepository;
import com.ltp.javauniversitylab4.repository.HouseRepository;
import com.ltp.javauniversitylab4.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    public void addOrUpdateHotel(HotelRequestDto hotelRequestDto) {
        String hotelName = hotelRequestDto.getHotelName();
        Hotel hotel = new Hotel(hotelName);
        hotelRepository.save(hotel);
    }

    public Hotel findHotelById(Long id) {
        return hotelRepository.findById(id).orElse(new Hotel(-1L));
    }

    public void deleteHotelById(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }

    public List<House> findALlHouses() {
        return houseRepository.findAll();
    }

    public Reserve findReserveByHouse(House house){
        return reserveRepository.findByHouse_HouseId(house.getHouseId());
    }


}
