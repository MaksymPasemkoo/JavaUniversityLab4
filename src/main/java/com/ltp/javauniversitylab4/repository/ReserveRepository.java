package com.ltp.javauniversitylab4.repository;

import com.ltp.javauniversitylab4.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Long> {

}
