package com.emlakjet.demo.service;

import com.emlakjet.demo.dto.UserDto;

public interface UserService {

    UserDto findByEmail(String email);
}
