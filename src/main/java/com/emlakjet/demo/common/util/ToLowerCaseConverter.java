package com.emlakjet.demo.common.util;

import com.fasterxml.jackson.databind.util.StdConverter;

public class ToLowerCaseConverter extends StdConverter<String, String> {
    @Override
    public String convert(String s) {
        return s.toLowerCase();
    }
}