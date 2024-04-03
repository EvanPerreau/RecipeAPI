package fr.evanperreau.recipeapi.repository;

import fr.evanperreau.recipeapi.model.RecipeIngredient;
import fr.evanperreau.recipeapi.model.RecipeIngredientId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, RecipeIngredientId> {
}
