package fr.evanperreau.recipeapi.repository;

import fr.evanperreau.recipeapi.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
