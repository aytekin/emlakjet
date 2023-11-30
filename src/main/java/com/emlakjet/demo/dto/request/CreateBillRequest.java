package com.emlakjet.demo.dto.request;

import com.emlakjet.demo.common.util.ToLowerCaseConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillRequest {

    private String billNo;
    private String productName;
    private double amount;
    private String name;
    private String lastname;
    @JsonDeserialize(converter = ToLowerCaseConverter.class)
    private String email;
}
