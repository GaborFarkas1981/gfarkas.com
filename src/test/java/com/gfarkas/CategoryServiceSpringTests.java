package com.gfarkas;

import com.gfarkas.dao.Category;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.repository.MediaMarktRepository;
import com.gfarkas.repository.MediaMarktRepositoryImpl;
import com.gfarkas.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.List;

@SpringBootTest
class CategoryServiceSpringTests {

	@Autowired
	CategoryService service;

	@Autowired
	MediaMarktRepositoryImpl repository;

	@BeforeEach
	public void clearAllCategory() {
		repository.deleteAll();
	}

	@Test
	public void createCategoryTest() {
		createCategory("Notebook");
		List<Category> categories = repository.findAll();
		Assertions.assertNotNull(categories);
		Assertions.assertEquals("Notebook", categories.get(0).getName());
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
		List<CategoryDto> receivedCategories = service.list();
		Assertions.assertNotNull(receivedCategories);
		Assertions.assertEquals(10, receivedCategories.size());
	}

	private void createCategory(String categoryName) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName(Objects.requireNonNullElse(categoryName, "catName"));
		repository.add(categoryDto);
	}
}
