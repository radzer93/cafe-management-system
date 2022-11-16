package org.radoslawzerek.cafemanagementsystem.restimpl;

import lombok.RequiredArgsConstructor;
import org.radoslawzerek.cafemanagementsystem.constants.CafeConstants;
import org.radoslawzerek.cafemanagementsystem.entity.Category;
import org.radoslawzerek.cafemanagementsystem.rest.CategoryRest;
import org.radoslawzerek.cafemanagementsystem.service.CategoryService;
import org.radoslawzerek.cafemanagementsystem.utils.CafeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CategoryRestImpl implements CategoryRest {

    private final CategoryService categoryService;

    @Override
    public ResponseEntity<String> addCategory(Map<String, String> requestMap) {
        try {
            return categoryService.addCategory(requestMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Category>> getCategories(String filterValue) {
        try {
            return categoryService.getCategories(filterValue);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try {
            return categoryService.updateCategory(requestMap);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
