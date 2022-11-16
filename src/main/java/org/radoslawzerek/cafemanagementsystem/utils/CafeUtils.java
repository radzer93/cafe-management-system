package org.radoslawzerek.cafemanagementsystem.utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor
public class CafeUtils {

    public static ResponseEntity<String> getResponseEntity(String response, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\"" + response + "\"}", httpStatus);
    }
}
