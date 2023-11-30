package com.emlakjet.demo.service;

import com.emlakjet.demo.dto.BillDto;
import com.emlakjet.demo.dto.request.CreateBillRequest;
import com.emlakjet.demo.dto.response.CreateBillResponse;

import java.util.List;

public interface BillService {
    CreateBillResponse createBill(CreateBillRequest request);

    List<BillDto> acceptedBillList();

    List<BillDto> rejectedBillList();
}
