package com.emlakjet.demo.controller;

import com.emlakjet.demo.dto.BillDto;
import com.emlakjet.demo.dto.request.CreateBillRequest;
import com.emlakjet.demo.dto.response.CreateBillResponse;
import com.emlakjet.demo.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/bill")
@AllArgsConstructor
public class BillController {
    private BillService billService;

    @Operation(summary = "Create Bill Endpoint", description = "in order to insert bill data.")
    @PostMapping
    public CreateBillResponse createBill(@RequestBody CreateBillRequest request) {
        return billService.createBill(request);
    }

    @Operation(summary = "Get Rejected Bill List Because of Limit Exceed")
    @GetMapping("/get-rejected-bill-list")
    public List<BillDto> getRejectedBillList() {
        return billService.rejectedBillList();
    }

    @Operation(summary = "Get Accepted Bill List")
    @GetMapping("/get-accepted-bill-list")
    public List<BillDto> getAcceptedBillList() {
        return billService.acceptedBillList();
    }
}
