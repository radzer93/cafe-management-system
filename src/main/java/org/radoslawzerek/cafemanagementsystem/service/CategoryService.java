package org.radoslawzerek.cafemanagementsystem.service;

import org.radoslawzerek.cafemanagementsystem.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    ResponseEntity<String> addCategory(Map<String, String> requestMap);

    ResponseEntity<List<Category>> getCategories(String filterValue);

    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
