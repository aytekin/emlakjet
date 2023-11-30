package com.emlakjet.demo.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractDtoId extends AbstractDto{
    private String id;
}
