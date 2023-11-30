package com.emlakjet.demo.repository;

import com.emlakjet.demo.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT COALESCE(SUM(amount), 0.0) FROM Bill WHERE email=?1")
    double getSumOfAmounts(String email);
}
