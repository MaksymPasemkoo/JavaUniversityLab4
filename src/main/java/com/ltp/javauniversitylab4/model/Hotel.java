package com.ltp.javauniversitylab4.model;

import com.ltp.javauniversitylab4.utils.HotelAmenitie;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    private String hotelName;
    @ElementCollection
    @CollectionTable(name = "hotel_amenities",joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<HotelAmenitie> hotelAmenities = new ArrayList<>(HotelAmenitie.getAmenities());

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public Hotel(Long hotelId) {
        this.hotelId = hotelId;
    }
}
