package fr.evanperreau.recipeapi.repository;

import fr.evanperreau.recipeapi.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
