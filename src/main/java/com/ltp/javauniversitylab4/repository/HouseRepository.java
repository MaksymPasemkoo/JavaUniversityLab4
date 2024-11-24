package com.ltp.javauniversitylab4.repository;

import com.ltp.javauniversitylab4.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {
}
