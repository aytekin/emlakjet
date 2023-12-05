package com.emlakjet.demo.security.service;

import com.emlakjet.demo.common.exception.EmlakjetException;
import com.emlakjet.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.emlakjet.demo.common.i18n.TranslationUtil.USER_NOT_FOUND;
import static com.emlakjet.demo.common.i18n.Translator.toLocale;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EmlakjetException(toLocale(USER_NOT_FOUND)));
    }
}
