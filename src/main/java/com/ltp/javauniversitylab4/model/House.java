package com.ltp.javauniversitylab4.model;

import com.ltp.javauniversitylab4.utils.HouseAmenitie;
import com.ltp.javauniversitylab4.utils.HouseCondition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;
    private HouseCondition houseCondition;
    @Value(value = "0")
    private int personInHouse;
    @ElementCollection
    @CollectionTable(name = "house_amenities",joinColumns = @JoinColumn(name = "house_id"))
    @Column(name = "amenity")
    private List<HouseAmenitie> houseAmenities = new ArrayList<>();
    @ManyToOne
    private Hotel hotel;

    public House(HouseCondition houseCondition,int personInHouse, Hotel hotel) {
        this.houseCondition = houseCondition;
        this.personInHouse = personInHouse;
        this.hotel = hotel;
        includeAmenities();
    }

    public House(Long houseId) {
        this.houseId = houseId;
    }


    private void includeAmenities(){
        switch (houseCondition.getTextCondition()) {
            case "economy" -> {
                houseAmenities.add(HouseAmenitie.WIFI);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.MINI_KITCHEN);
                personInHouse++;
            }
            case "standard" -> {
                houseAmenities.add(HouseAmenitie.WIFI);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.MINI_KITCHEN);
                houseAmenities.add(HouseAmenitie.CONDITIONER);
                houseAmenities.add(HouseAmenitie.SAFE);
                personInHouse++;
            }
            case "luxury" -> {
                houseAmenities.add(HouseAmenitie.WIFI);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.KITCHEN);
                houseAmenities.add(HouseAmenitie.CONDITIONER);
                houseAmenities.add(HouseAmenitie.SAFE);
                houseAmenities.add(HouseAmenitie.SMART_TV);
                houseAmenities.add(HouseAmenitie.WASHING_MACHINE);
                personInHouse += 2;
            }
            case "president" -> {
                houseAmenities.add(HouseAmenitie.WIFI);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.BED);
                houseAmenities.add(HouseAmenitie.KITCHEN);
                houseAmenities.add(HouseAmenitie.CONDITIONER);
                houseAmenities.add(HouseAmenitie.SAFE);
                houseAmenities.add(HouseAmenitie.SMART_TV);
                houseAmenities.add(HouseAmenitie.WASHING_MACHINE);
                houseAmenities.add(HouseAmenitie.TERRACE);
                houseAmenities.add(HouseAmenitie.JACUZZI);
                personInHouse += 3;
            }
        }
    }




}
