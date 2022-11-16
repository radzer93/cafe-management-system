package org.radoslawzerek.cafemanagementsystem.dao;

import org.radoslawzerek.cafemanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Long> {

    List<Category> getAllCategory();
}
