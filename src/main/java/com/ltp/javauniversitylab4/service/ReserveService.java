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

        Reserve reserve = new Reserve(house,client,localDate);

        if (!houseRepository.existsById(house.getHouseId()) && !clientRepository.existsById(client.getClientId())) {
            reserveRepository.save(reserve);

            double priceForHouse = priceOfHouse(house);

            if (localDate.getMonth() == Month.NOVEMBER || localDate.getMonth() == Month.MARCH) {
                double discountInPercentage = 20;
                double discount = discountInPercentage / 100;
                double totalPriceForHouseWithDiscount = priceForHouse - priceForHouse * discount;

                if (client.getBalance() >= totalPriceForHouseWithDiscount) {
                    client.decrementBalance(totalPriceForHouseWithDiscount);
                } else {
                    throw new BookingException("Not enough money to pay.");
                }
            } else {
                if (client.getBalance() >= priceForHouse) {
                    client.decrementBalance(priceForHouse);
                } else {
                    throw new BookingException("Not enough money to pay.");
                }
            }
            reserveRepository.save(reserve);
        }
        else if (clientRepository.existsById(client.getClientId())) {
            throw new BookingException(client.getName() + " has already reserved house.");
        } else {
            throw new BookingException("The house is already reserved.");
        }

        return reserve;
    }

    private double priceOfHouse(House house) {
        return house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenitie::getPrice)
                .sum();
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
