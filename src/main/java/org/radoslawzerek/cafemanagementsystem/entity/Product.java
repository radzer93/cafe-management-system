package org.radoslawzerek.cafemanagementsystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@NamedQuery(name = "Product.getAllProducts", query = "select new org.radoslawzerek.cafemanagementsystem.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p")
@NamedQuery(name = "Product.updateProductStatus", query = "update Product p set p.status=:status where p.id=:id")
@NamedQuery(name = "Product.getProductByCategory", query = "select new org.radoslawzerek.cafemanagementsystem.wrapper.ProductWrapper(p.id, p.name) from Product p where p.category.id=:id and p.status='true'")
@NamedQuery(name = "Product.getProductById", query = "select new org.radoslawzerek.cafemanagementsystem.wrapper.ProductWrapper(p.id, p.name, p.description, p.price) from Product p where p.id=:id")
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class Product implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;

    private String description;

    private Integer price;

    private String status;
}
