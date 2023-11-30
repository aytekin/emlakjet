package com.emlakjet.demo.service.impl;

import com.emlakjet.demo.common.exception.EmlakjetException;
import com.emlakjet.demo.common.exception.response.Code;
import com.emlakjet.demo.common.util.Mapper;
import com.emlakjet.demo.dto.request.CreateBillRequest;
import com.emlakjet.demo.dto.response.CreateBillResponse;
import com.emlakjet.demo.entity.Bill;
import com.emlakjet.demo.repository.BillRepository;
import com.emlakjet.demo.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    @Value("${application.billing.limit}")
    private int LIMIT;

    @Override
    public CreateBillResponse createBill(CreateBillRequest request) {
        var totalAmount = billRepository.getSumOfAmounts(request.getEmail());
        if (totalAmount + request.getAmount() >= LIMIT) {
            throw new EmlakjetException(Code.BILL_LIMIT_EXCEED, "Billing Limit exceed");
        }
        billRepository.save(Mapper.map(request, Bill.class));

        return CreateBillResponse.builder().totalAmount(totalAmount + request.getAmount()).build();
    }
}
