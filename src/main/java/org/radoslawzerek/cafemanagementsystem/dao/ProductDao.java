package org.radoslawzerek.cafemanagementsystem.dao;

import org.radoslawzerek.cafemanagementsystem.entity.Product;
import org.radoslawzerek.cafemanagementsystem.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    List<ProductWrapper> getAllProducts();

    @Modifying
    @Transactional
    Integer updateProductStatus(@Param("status") String status, @Param("id") long id);

    List<ProductWrapper> getProductByCategory(@Param("id") Long id);

    ProductWrapper getProductById(@Param("id") Long id);
}
