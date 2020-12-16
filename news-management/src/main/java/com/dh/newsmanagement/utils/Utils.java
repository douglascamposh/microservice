package com.dh.newsmanagement.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class Utils {
    public static Long validateHeaderNumber(HttpServletRequest request, String header) {
        try {
            return Long.valueOf(request.getHeader(header));
        } catch(Exception e) {
            log.error(String.format("Error in the format header field: %s, number is required", header), e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Error in the format header field: %s, number is required", header));
        }
    }
}
