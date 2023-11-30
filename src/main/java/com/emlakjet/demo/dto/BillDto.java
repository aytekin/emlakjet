package com.emlakjet.demo.dto;

import com.emlakjet.demo.dto.base.AbstractDtoId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillDto extends AbstractDtoId {
    private String billNo;
    private String productName;
    private double amount;
    private String name;
    private String lastname;
    private String email;
    private Boolean isAccepted;
}
