package com.ltp.javauniversitylab4.repository;

import com.ltp.javauniversitylab4.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    Hotel findByHotelName(String hotelName);
}
