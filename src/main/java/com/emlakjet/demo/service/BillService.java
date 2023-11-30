package com.emlakjet.demo.service;

import com.emlakjet.demo.dto.request.CreateBillRequest;
import com.emlakjet.demo.dto.response.CreateBillResponse;

public interface BillService {
    CreateBillResponse createBill(CreateBillRequest request);
}
