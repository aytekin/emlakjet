package com.emlakjet.demo.entity;

import com.emlakjet.demo.entity.base.AbstractEntityId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User extends AbstractEntityId {
    private String name;
    private String lastname;
    private String email;
    private String password;
}
