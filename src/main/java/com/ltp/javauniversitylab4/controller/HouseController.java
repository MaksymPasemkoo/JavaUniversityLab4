package com.ltp.javauniversitylab4.controller;

import com.ltp.javauniversitylab4.dto.request.HouseRequestDto;
import com.ltp.javauniversitylab4.dto.response.HouseResponseDto;
import com.ltp.javauniversitylab4.model.House;
import com.ltp.javauniversitylab4.service.HouseService;
import com.ltp.javauniversitylab4.utils.HotelAmenitie;
import com.ltp.javauniversitylab4.utils.HouseAmenitie;
import com.ltp.javauniversitylab4.utils.HouseCondition;
import com.ltp.javauniversitylab4.utils.HouseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HouseController {
    @Autowired
    private HouseService houseService;


    @PostMapping("/house")
    public ResponseEntity<String> addHouse(@RequestBody HouseRequestDto houseRequestDto) {
        houseService.addOrUpdateHouse(houseRequestDto);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<HouseResponseDto> findHouseById(@PathVariable Long id) {
        House house = houseService.findHouseById(id);

        HouseResponseDto houseResponseDto = HouseHelper.convertToResponseDto(house);
        if (houseResponseDto.getHouseId() != -1) {
            return new ResponseEntity<>(houseResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/house")
    public ResponseEntity<String> updateHouse(@RequestBody HouseRequestDto houseRequestDto) {
        houseService.addOrUpdateHouse(houseRequestDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/house/{id}")
    public ResponseEntity<String> deleteHouseById(@PathVariable Long id) {
        House house = houseService.findHouseById(id);

        if(house.getHouseId() != -1 ) {
            houseService.deleteHouseById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/houses")
    public ResponseEntity<List<HouseResponseDto>> findAllHouses() {
        List<House> houses = houseService.findAllHouses();

        List<HouseResponseDto> houseResponseDtos = houses.stream()
                .map(HouseHelper::convertToResponseDto)
                .toList();

        if(!houseResponseDtos.isEmpty()) {
            return new ResponseEntity<>(houseResponseDtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/houseAmenities/{id}")
    public ResponseEntity<List<HouseAmenitie>> getHouseAmenities(@PathVariable Long id) {
        House house = houseService.findHouseById(id);
        if(house.getHouseId() != -1) {
            return new ResponseEntity<>(house.getHouseAmenities(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/amenitie/{id}")
    public ResponseEntity<String> addAdditionalAmenities(@PathVariable Long id, @RequestParam String choice) {
        HouseAmenitie enumChoice = HouseAmenitie.fromString(choice);
        House house = houseService.findHouseById(id);

        HouseRequestDto houseRequestDto = HouseHelper.convertToRequestDto(house);

        if (enumChoice == HouseAmenitie.BED || enumChoice == HouseAmenitie.CHILD_BED) {
            houseRequestDto.setPersonInHouse(houseRequestDto.getPersonInHouse() + 1);
        }

        if (enumChoice != null) {
            house.getHouseAmenities().add(enumChoice);
            houseService.updateHouse(house);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/amenities/{id}")
    public ResponseEntity<List<String>> findAllAmenities(@PathVariable Long id) {
        House house = houseService.findHouseById(id);
        List<String> hotelAndHouseAmenities = new ArrayList<>();

        if(house.getHouseId() == -1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HotelAmenitie.getAmenities().stream()
                .map(HotelAmenitie::getAmenitieText)
                .forEach(hotelAndHouseAmenities::add);

        house.getHouseAmenities().stream()
                .map(HouseAmenitie::getAmenitiesText)
                .forEach(hotelAndHouseAmenities::add);


        return new ResponseEntity<>(hotelAndHouseAmenities, HttpStatus.OK);
    }


    @GetMapping("/house/condition/{houseCondition}")
    public ResponseEntity<List<HouseResponseDto>> printHousesByCondition(@PathVariable String houseCondition) {
        HouseCondition houseConditionEnum = HouseCondition.valueOf(houseCondition.toUpperCase());
        List<House> houseList = houseService.findAllHouses().stream()
                .filter(house -> house.getHouseCondition().getTextCondition().equals(houseConditionEnum.getTextCondition()))
                .toList();

        List<HouseResponseDto> houseResponseDtos = houseList.stream()
                .map(HouseHelper::convertToResponseDto)
                .toList();
        return new ResponseEntity<>(houseResponseDtos,HttpStatus.OK);
    }

    @GetMapping("/house/amenitie/{houseAmenitie}")
    public ResponseEntity<List<HouseResponseDto>> printHousesByAmenity(@PathVariable String houseAmenitie) {
        HouseAmenitie houseAmenitieEnum = HouseAmenitie.valueOf(houseAmenitie.toUpperCase());
        List<House> houseList = houseService.findAllHouses().stream()
                .filter(house -> house.getHouseAmenities().contains(houseAmenitieEnum))
                .toList();

        List<HouseResponseDto> houseResponseDtos = houseList.stream()
                .map(HouseHelper::convertToResponseDto)
                .toList();
        return new ResponseEntity<>(houseResponseDtos,HttpStatus.OK);
    }

}
