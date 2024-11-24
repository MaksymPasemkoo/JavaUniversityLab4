package com.ltp.javauniversitylab4.controller;

import com.ltp.javauniversitylab4.dto.request.ReserveRequestDto;
import com.ltp.javauniversitylab4.dto.response.ReserveResponseDto;
import com.ltp.javauniversitylab4.model.Reserve;
import com.ltp.javauniversitylab4.service.ReserveService;
import com.ltp.javauniversitylab4.utils.ReserveHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReserveController {
    @Autowired
    private final ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> addReserve(@RequestBody final ReserveRequestDto reserveDto) {
        final Reserve reserve = reserveService.addOrUpdateReserve(reserveDto);
        if (reserve.getHouse().getHouseId() != -1 || reserve.getClient().getClientId() != -1) {
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/reserve/{id}")
    public ResponseEntity<ReserveResponseDto> findClientById(final @PathVariable Long id) {
        final Reserve reserve = reserveService.findReserveById(id);
        final ReserveResponseDto reserveResponseDto = ReserveHelper.convertToResponseDto(reserve);

        if (reserveResponseDto.getReserveId() != -1) {
            return new ResponseEntity<>(reserveResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reserve")
    public ResponseEntity<String> updateReserve(@RequestBody final ReserveRequestDto reserveDto) {
        final Reserve reserve = reserveService.addOrUpdateReserve(reserveDto);
        if (reserve.getHouse().getHouseId() != -1 || reserve.getClient().getClientId() != -1) {
            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/reserve/{id}")
    public ResponseEntity<String> deleteReserveById(@PathVariable final Long id) {
        final Reserve reserve = reserveService.findReserveById(id);
        if (reserve.getReserveId() != -1) {
            reserveService.deleteReserveById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reserves")
    public ResponseEntity<List<Reserve>> findAllReserves() {
        final List<Reserve> reserves = reserveService.findAllReserves();
        if (!reserves.isEmpty()) {
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/calendar/{month}")
    public ResponseEntity<List<Integer>> calendarOfReservation(@PathVariable final String month) {
        final Month monthEnum = Month.valueOf(month.toUpperCase());
        final int year = LocalDate.now().getYear();
        final LocalDate firstDayOfMonth = LocalDate.of(year, monthEnum, 1);

        final int monthLength = monthEnum.length(firstDayOfMonth.isLeapYear());
        final List<Integer> calendar = new ArrayList<>();
        for (int i = 1; i <= monthLength; i++) {
            final LocalDate currentDay = firstDayOfMonth.withDayOfMonth(i);

            final boolean isReserved = reserveService.findAllReserves().stream()
                    .anyMatch(reserve -> reserve.getDate().equals(currentDay));
            if (!isReserved) {
                calendar.add(i);
            }
        }
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }


}
