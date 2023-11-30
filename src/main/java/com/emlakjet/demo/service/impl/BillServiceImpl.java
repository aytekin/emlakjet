package com.emlakjet.demo.service.impl;

import com.emlakjet.demo.common.exception.EmlakjetException;
import com.emlakjet.demo.common.exception.response.Code;
import com.emlakjet.demo.common.util.Mapper;
import com.emlakjet.demo.dto.BillDto;
import com.emlakjet.demo.dto.request.CreateBillRequest;
import com.emlakjet.demo.dto.response.CreateBillResponse;
import com.emlakjet.demo.entity.Bill;
import com.emlakjet.demo.repository.BillRepository;
import com.emlakjet.demo.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.emlakjet.demo.common.i18n.TranslationUtil.BILL_LIMIT_EXCEED;
import static com.emlakjet.demo.common.i18n.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    @Value("${application.billing.limit}")
    private int LIMIT;

    @Override
    public CreateBillResponse createBill(CreateBillRequest request) {
        var totalAmount = billRepository.getSumOfAmounts(request.getEmail());
        Bill bill = Mapper.map(request, Bill.class);
        if (totalAmount + request.getAmount() >= LIMIT) {
            bill.setIsAccepted(Boolean.FALSE);
            billRepository.save(bill);
            throw new EmlakjetException(Code.BILL_LIMIT_EXCEED, toLocale(BILL_LIMIT_EXCEED));
        }
        billRepository.save(bill);

        return CreateBillResponse.builder()
                .billDto(Mapper.map(bill, BillDto.class))
                .totalAmount(totalAmount + request.getAmount())
                .build();
    }

    @Override
    public List<BillDto> acceptedBillList() {
        return Mapper.mapAll(billRepository.findAllByIsAcceptedTrue(), BillDto.class);
    }

    @Override
    public List<BillDto> rejectedBillList() {
        return Mapper.mapAll(billRepository.findAllByIsAcceptedFalse(), BillDto.class);
    }
}
