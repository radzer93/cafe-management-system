package org.radoslawzerek.cafemanagementsystem.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.radoslawzerek.cafemanagementsystem.JWT.JwtFilter;
import org.radoslawzerek.cafemanagementsystem.constants.CafeConstants;
import org.radoslawzerek.cafemanagementsystem.dao.ProductDao;
import org.radoslawzerek.cafemanagementsystem.entity.Category;
import org.radoslawzerek.cafemanagementsystem.entity.Product;
import org.radoslawzerek.cafemanagementsystem.service.ProductService;
import org.radoslawzerek.cafemanagementsystem.utils.CafeUtils;
import org.radoslawzerek.cafemanagementsystem.wrapper.ProductWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (this.validateProductMap(requestMap, false)) {
                    productDao.save(this.getProductFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Product was added successfully!", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProducts() {
        try {
            return new ResponseEntity<>(productDao.getAllProducts(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (this.validateProductMap(requestMap, true)) {
                    Optional<Product> productDaoById = productDao.findById(Long.parseLong(requestMap.get("id")));
                    if (productDaoById.isPresent()) {
                        Product productFromMap = this.getProductFromMap(requestMap, true);
                        productFromMap.setStatus(productDaoById.get().getStatus());
                        productDao.save(productFromMap);
                        return CafeUtils.getResponseEntity("Product was updated successfully!", HttpStatus.OK);
                    } else
                        return CafeUtils.getResponseEntity("Product with id " + requestMap.get("id") + " does not exists", HttpStatus.NOT_FOUND);
                } else return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> product = productDao.findById(id);
                if (product.isPresent()) {
                    productDao.deleteById(id);
                    return CafeUtils.getResponseEntity("Product was deleted successfully!", HttpStatus.OK);
                } else
                    return CafeUtils.getResponseEntity("Product with id " + id + " does not exists", HttpStatus.NOT_FOUND);
            } else return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Product> product = productDao.findById(Long.parseLong(requestMap.get("id")));
                if (product.isPresent()) {
                    productDao.updateProductStatus(requestMap.get("status"), Long.parseLong(requestMap.get("id")));
                    return CafeUtils.getResponseEntity("Product status was updated successfully!", HttpStatus.OK);
                } else
                    return CafeUtils.getResponseEntity("Product with id " + requestMap.get("id") + " does not exists", HttpStatus.NOT_FOUND);
            } else return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Long id) {
        try {
            return new ResponseEntity<>(productDao.getProductByCategory(id), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getById(Long id) {
        try {
            return new ResponseEntity<>(productDao.getProductById(id), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Long.parseLong(requestMap.get("categoryId")));
        Product product = new Product();
        if (isAdd) {
            product.setId(Long.parseLong(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }

    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else return !validateId;
        }
        return false;
    }
}
