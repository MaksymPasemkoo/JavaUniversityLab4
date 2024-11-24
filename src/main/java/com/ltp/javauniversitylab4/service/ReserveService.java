package com.ltp.javauniversitylab4.service;

import com.ltp.javauniversitylab4.controller.ReserveController;
import com.ltp.javauniversitylab4.dto.request.ReserveRequestDto;
import com.ltp.javauniversitylab4.model.Client;
import com.ltp.javauniversitylab4.model.House;
import com.ltp.javauniversitylab4.model.Reserve;
import com.ltp.javauniversitylab4.repository.ClientRepository;
import com.ltp.javauniversitylab4.repository.HouseRepository;
import com.ltp.javauniversitylab4.repository.ReserveRepository;
import com.ltp.javauniversitylab4.utils.BookingException;
import com.ltp.javauniversitylab4.utils.HouseAmenitie;
import com.ltp.javauniversitylab4.utils.HouseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ClientRepository clientRepository;


    public Reserve addOrUpdateReserve(ReserveRequestDto reserveRequestDto) {
        House house = houseRepository.findById(reserveRequestDto.getHouseId()).orElse(new House(-1L));
        Client client = clientRepository.findById(reserveRequestDto.getHouseId()).orElse(new Client(-1L));

        LocalDate localDate = reserveRequestDto.getDate();

        Reserve checkHouseReserve = reserveRepository.findByHouse_HouseId(house.getHouseId());
        Reserve checkClientReserve = reserveRepository.findByClient_ClientId(client.getClientId());
        if (checkHouseReserve != null) {
            throw new BookingException("The house is already reserved.");
        }
        if (checkClientReserve != null){
            throw new BookingException("The client has already reserved the house.");
        }
        Reserve newReserve = new Reserve(house, client, localDate);
        reserveRepository.save(newReserve);

        Month month = localDate.getMonth();
        double priceForHouse = HouseHelper.priceOfHouse(house, month);
        if (client.getBalance() >= priceForHouse) {
            client.decrementBalance(priceForHouse);
        } else {
            throw new BookingException("Not enough money to pay.");
        }

        return newReserve;
    }


    public Reserve findReserveById(Long id) {
        return reserveRepository.findById(id).orElse(new Reserve(-1L));
    }


    public void deleteReserveById(Long id) {
        reserveRepository.deleteById(id);
    }

    public List<Reserve> findAllReserves() {
        return reserveRepository.findAll();
    }
}
