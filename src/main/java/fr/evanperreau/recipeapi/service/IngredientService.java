package fr.evanperreau.recipeapi.service;

import fr.evanperreau.recipeapi.model.Ingredient;
import fr.evanperreau.recipeapi.repository.IngredientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Iterable<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> getIngredient(final long id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient saveOrUpdateIngredient(final Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(final long id) {
        ingredientRepository.deleteById(id);
    }

    public void deleteIngredients() {
        ingredientRepository.deleteAll();
    }
}
