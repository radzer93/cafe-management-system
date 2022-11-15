package org.radoslawzerek.cafemanagementsystem.dao;

import org.radoslawzerek.cafemanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmailId(@Param("email") String email);
}
