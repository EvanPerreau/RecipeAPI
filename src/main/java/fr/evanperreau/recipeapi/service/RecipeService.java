package fr.evanperreau.recipeapi.service;

import fr.evanperreau.recipeapi.model.Recipe;
import fr.evanperreau.recipeapi.repository.RecipeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;


    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> getRecipe(final long id) {
        return recipeRepository.findById(id);
    }

    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public void deleteRecipe(final long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe saveOrUpdateRecipe(final Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteAllRecipes() {
        recipeRepository.deleteAll();
    }
}
