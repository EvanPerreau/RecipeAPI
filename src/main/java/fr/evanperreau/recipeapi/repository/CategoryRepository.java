package fr.evanperreau.recipeapi.repository;

import fr.evanperreau.recipeapi.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
