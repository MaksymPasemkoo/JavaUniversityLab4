package com.ltp.javauniversitylab4.service;

import com.ltp.javauniversitylab4.dto.request.ReserveRequestDto;
import com.ltp.javauniversitylab4.model.Client;
import com.ltp.javauniversitylab4.model.House;
import com.ltp.javauniversitylab4.model.Reserve;
import com.ltp.javauniversitylab4.repository.ClientRepository;
import com.ltp.javauniversitylab4.repository.HouseRepository;
import com.ltp.javauniversitylab4.repository.ReserveRepository;
import com.ltp.javauniversitylab4.utils.BookingException;
import com.ltp.javauniversitylab4.utils.HouseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private final ReserveRepository reserveRepository;

    @Autowired
    private final HouseRepository houseRepository;

    @Autowired
    private final ClientRepository clientRepository;

    public ReserveService(ReserveRepository reserveRepository, HouseRepository houseRepository, ClientRepository clientRepository) {
        this.reserveRepository = reserveRepository;
        this.houseRepository = houseRepository;
        this.clientRepository = clientRepository;
    }


    public Reserve addOrUpdateReserve(final ReserveRequestDto reserveRequestDto) {
        final House house = houseRepository.findById(reserveRequestDto.getHouseId()).orElse(new House(-1L));
        final Client client = clientRepository.findById(reserveRequestDto.getHouseId()).orElse(new Client(-1L));

        final LocalDate localDate = reserveRequestDto.getDate();

        final Reserve checkHouseReserve = reserveRepository.findByHouse_HouseId(house.getHouseId());
        final Reserve checkClientReserve = reserveRepository.findByClient_ClientId(client.getClientId());

        if (checkHouseReserve != null) {
            throw new BookingException("The house is already reserved.");
        }

        if (checkClientReserve != null){
            throw new BookingException("The client has already reserved the house.");
        }

        final Reserve newReserve = new Reserve(house, client, localDate);
        reserveRepository.save(newReserve);

        final Month month = localDate.getMonth();
        final double priceForHouse = HouseHelper.priceOfHouse(house, month);
        if (client.getBalance() >= priceForHouse) {
            client.decrementBalance(priceForHouse);
        } else {
            throw new BookingException("Not enough money to pay.");
        }

        return newReserve;
    }


    public Reserve findReserveById(final Long id) {
        return reserveRepository.findById(id).orElse(new Reserve(-1L));
    }


    public void deleteReserveById(final Long id) {
        reserveRepository.deleteById(id);
    }

    public List<Reserve> findAllReserves() {
        return reserveRepository.findAll();
    }
}
