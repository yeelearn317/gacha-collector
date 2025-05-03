package com.gacha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "brand", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Brand extends BaseEntity {
    private String name;
}
