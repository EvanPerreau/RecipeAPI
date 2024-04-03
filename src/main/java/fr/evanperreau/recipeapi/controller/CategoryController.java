package fr.evanperreau.recipeapi.controller;

import fr.evanperreau.recipeapi.model.Category;
import fr.evanperreau.recipeapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public Iterable<Category> getCategories() {
        return categoryService.getCategories();
    }
}
