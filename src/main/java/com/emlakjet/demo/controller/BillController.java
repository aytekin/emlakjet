package com.emlakjet.demo.controller;

import com.emlakjet.demo.dto.request.CreateBillRequest;
import com.emlakjet.demo.dto.response.CreateBillResponse;
import com.emlakjet.demo.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bill")
@AllArgsConstructor
public class BillController {
    private BillService billService;

    @PostMapping
    public CreateBillResponse createBill(@RequestBody CreateBillRequest request){
        return billService.createBill(request);
    }
}
