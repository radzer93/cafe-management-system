package org.radoslawzerek.cafemanagementsystem.rest;

import org.radoslawzerek.cafemanagementsystem.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("/category")
public interface CategoryRest {

    @PostMapping("/add")
    ResponseEntity<String> addCategory(@RequestBody Map<String, String> requestMap);

    @GetMapping("/get")
    ResponseEntity<List<Category>> getCategories(@RequestParam(required = false) String filterValue);

    @PostMapping("/update")
    ResponseEntity<String> updateCategory(@RequestBody Map<String, String> requestMap);
}
