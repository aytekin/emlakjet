package com.emlakjet.demo.dto.base;

import lombok.Data;

import java.util.Date;

@Data
public class AbstractDto {
    private Date createdDate;

    private Date updatedDate;
}
