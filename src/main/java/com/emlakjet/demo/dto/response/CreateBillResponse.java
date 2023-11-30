package com.emlakjet.demo.dto.response;

import com.emlakjet.demo.dto.BillDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBillResponse {
   private double totalAmount;
   private BillDto billDto;
}
