package com.gfarkas.repository;

import com.gfarkas.dao.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity getCategoryEntityByName(String name);
}
