package org.radoslawzerek.cafemanagementsystem.dao;

import org.radoslawzerek.cafemanagementsystem.entity.User;
import org.radoslawzerek.cafemanagementsystem.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUser();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Long id);

    List<String> getAllAdmin();
}
