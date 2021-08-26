package com.gfarkas.repository;

import com.gfarkas.dao.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Set<ProductEntity> findByCategoryEntity_Id(Long categoryId);
    Set<ProductEntity> findByBrand(String brand);
    Set<ProductEntity> findByDescription(String description);
}
