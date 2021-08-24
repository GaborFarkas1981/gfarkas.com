package com.gfarkas.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    private Long id;

    @Column(name = "description")
    @Size(max = 1024)
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "brand")
    @Size(max = 1024)
    private String brand;

    @Column(name = "size")
    private Integer size;

    @Column(name = "os")
    @Size(max = 1024)
    private String os;

    @ManyToOne
    @JoinColumn(name = "category_id")
    CategoryEntity categoryEntity;

}
