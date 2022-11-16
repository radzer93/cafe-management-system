package org.radoslawzerek.cafemanagementsystem.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@NamedQuery(name = "Category.getAllCategory", query = "select c from Category c where c.id in" +
        " (select p.category from Product p where p.status='true')")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class Category implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
