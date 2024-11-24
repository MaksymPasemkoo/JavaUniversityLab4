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
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final HouseRepository houseRepository;

    @Autowired
    private final ReserveRepository reserveRepository;

    public HotelService(final HotelRepository hotelRepository, final HouseRepository houseRepository
            , final ReserveRepository reserveRepository) {
        this.hotelRepository = hotelRepository;
        this.houseRepository = houseRepository;
        this.reserveRepository = reserveRepository;
    }

    public void addOrUpdateHotel(final HotelRequestDto hotelRequestDto) {
        final String hotelName = hotelRequestDto.getHotelName();
        final Hotel hotel = new Hotel(hotelName);
        hotelRepository.save(hotel);
    }

    public Hotel findHotelById(final Long id) {
        return hotelRepository.findById(id).orElse(new Hotel(-1L));
    }

    public void deleteHotelById(final Long id) {
        hotelRepository.deleteById(id);
    }

    public List<Hotel> findAllHotels() {
        return hotelRepository.findAll();
    }

    public List<House> findALlHouses() {
        return houseRepository.findAll();
    }

    public Reserve findReserveByHouse(final House house) {
        return reserveRepository.findByHouse_HouseId(house.getHouseId());
    }


}
