package com.emlakjet.demo.entity;

import com.emlakjet.demo.entity.base.AbstractEntityId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_bill")
public class Bill extends AbstractEntityId {
    @Column(name="bill_no")
    private String billNo;
    @Column(name="product_name")
    private String productName;
    private float amount;
    private String name;
    private String lastname;
    private String email;
}
