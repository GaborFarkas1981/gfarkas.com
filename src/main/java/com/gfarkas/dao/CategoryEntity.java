package com.gfarkas.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {

    @OneToMany(mappedBy = "categoryEntity")
    private Set<ProductEntity> productEntities = new HashSet<>();

    @Id
    private Long id;

    @Column(name = "name")
    @Size(max = 1024)
    private String name;
}
