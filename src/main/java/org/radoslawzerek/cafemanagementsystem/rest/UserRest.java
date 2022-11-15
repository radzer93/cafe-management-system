package org.radoslawzerek.cafemanagementsystem.rest;

import org.radoslawzerek.cafemanagementsystem.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
public interface UserRest {

    @PostMapping("/signup")
    ResponseEntity<String> signup(@RequestBody Map<String, String> requestMap);

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Map<String, String> requestMap);

    @GetMapping("/get")
    ResponseEntity<List<UserWrapper>> getAllUsers();

    @PostMapping("/update")
    ResponseEntity<String> update(@RequestBody Map<String, String> requestMap);
}
