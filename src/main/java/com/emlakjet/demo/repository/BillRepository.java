package com.emlakjet.demo.repository;

import com.emlakjet.demo.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT COALESCE(SUM(amount), 0.0) FROM Bill WHERE email=?1 and isAccepted=true")
    double getSumOfAmounts(String email);

    List<Bill> findAllByIsAcceptedTrue();
    List<Bill> findAllByIsAcceptedFalse();
}
