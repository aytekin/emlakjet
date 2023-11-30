package com.emlakjet.demo.dto;

import com.emlakjet.demo.dto.base.AbstractDtoId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto  extends AbstractDtoId {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
