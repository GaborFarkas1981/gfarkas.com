package com.gfarkas;

import com.gfarkas.dto.CategoryDto;
import com.gfarkas.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class CategoryServiceSpringTests {

	@Autowired
	CategoryService service;

	@Autowired
	CategoryRepository repository;

	@BeforeEach
	public void clearAllProduct() {
		repository.deleteAll();
	}

	@Test
	public void createCategoryTest() {
		CategoryDto savedCategory = createCategory("Notebook");
		Assertions.assertNotNull(savedCategory);
		Assertions.assertEquals("Notebook", savedCategory.getName());
	}

	@Test
	public void getCategoryTest() {
		createCategory("Notebook");
		CategoryDto receivedCategory = service.getByName("Notebook");
		Assertions.assertNotNull(receivedCategory);
		Assertions.assertEquals("Notebook", receivedCategory.getName());
	}

	@Test
	public void listCategoryTest() {
		for (long i = 0L; i < 10 ; i++) {
			createCategory("Note" + i);
		}
		Set<CategoryDto> receivedCategorys = service.list();
		Assertions.assertNotNull(receivedCategorys);
		Assertions.assertEquals(10, receivedCategorys.size());
	}

	private CategoryDto createCategory(String categoryName) {
		CategoryDto categoryDto = new CategoryDto();
		if (categoryName == null) {
			categoryDto.setName("catName");
		} else {
			categoryDto.setName(categoryName);
		}

		return service.create(categoryDto);
	}
}
