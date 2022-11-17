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

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email =:email")
@NamedQuery(name = "User.getAllUser", query = "select new org.radoslawzerek.cafemanagementsystem.wrapper.UserWrapper(u.id, u.name, u.surname," +
        " u.email, u.phone, u.status) from User u where u.role='user'")
@NamedQuery(name = "User.getAllAdmin", query = "select u.email from User u where u.role='admin'")
@NamedQuery(name = "User.updateStatus", query = "update User u set u.status=:status where u.id=:id")
@Data
@Entity
@DynamicInsert
@DynamicUpdate
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private String password;

    private String status;

    private String role;
}
