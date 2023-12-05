package com.emlakjet.demo.dto;

import com.emlakjet.demo.dto.base.AbstractDtoId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto  extends AbstractDtoId {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
