package fr.evanperreau.recipeapi.service;

import fr.evanperreau.recipeapi.model.RecipeIngredient;
import fr.evanperreau.recipeapi.model.RecipeIngredientId;
import fr.evanperreau.recipeapi.repository.RecipeIngredientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class RecipeIngredientService {

    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    public void deleteRecipeIngredient(final RecipeIngredientId id) {
        recipeIngredientRepository.deleteById(id);
    }

    public RecipeIngredient saveOrUpdateRecipeIngredient(final RecipeIngredient recipeIngredient) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    public Iterable<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredientRepository.findAll();
    }

    public Optional<RecipeIngredient> getRecipeIngredient(final RecipeIngredientId id) {
        return recipeIngredientRepository.findById(id);
    }

    public void deleteRecipesIngredients() {
        recipeIngredientRepository.deleteAll();
    }
}
