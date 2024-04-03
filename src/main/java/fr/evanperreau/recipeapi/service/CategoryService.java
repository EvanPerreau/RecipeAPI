package fr.evanperreau.recipeapi.service;

import fr.evanperreau.recipeapi.model.Category;
import fr.evanperreau.recipeapi.repository.CategoryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(final long id) {
        return categoryRepository.findById(id);
    }

    public Category saveOrUpdateCategory(final Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(final long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }
}
