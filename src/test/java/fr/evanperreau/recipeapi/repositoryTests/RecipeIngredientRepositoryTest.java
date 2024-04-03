package fr.evanperreau.recipeapi.repositoryTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.*;
import fr.evanperreau.recipeapi.repository.IngredientRepository;
import fr.evanperreau.recipeapi.repository.RecipeIngredientRepository;
import fr.evanperreau.recipeapi.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeIngredientRepositoryTest {

    @Autowired
    // RecipeIngredientRepository is used to interact with the RecipeIngredient table in the database
    private RecipeIngredientRepository recipeIngredientRepository;
    @Autowired
    // RecipeRepository is used to interact with the Recipe table in the
    private RecipeRepository recipeRepository;
    @Autowired
    // IngredientRepository is used to interact with the Ingredient table in the database
    private IngredientRepository ingredientRepository;

    /**
     * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(recipeIngredientRepository).isNotNull();
    }

    /**
     * Test if a recipe ingredient can be saved
     */
    @Test
    void testSaveRecipeIngredient() {
        // Create a new recipe ingredient instance
        RecipeIngredient newRecipeIngredient = new RecipeIngredient();

        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientRepository.save(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeRepository.save(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientRepository.save(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Check if the recipe ingredient in the database is the same as the new recipe ingredient
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).get()).isEqualTo(newRecipeIngredient);
    }

    /**
     * Test if a recipe ingredient can be deleted
     */
    @Test
    void testDeleteRecipeIngredient() {
        // Create a new recipe ingredient instance
        RecipeIngredient newRecipeIngredient = new RecipeIngredient();

        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientRepository.save(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeRepository.save(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientRepository.save(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Delete the recipe ingredient from the database
        recipeIngredientRepository.delete(newRecipeIngredient);

        // Check if the recipe ingredient is not present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isFalse();
    }

    /**
     * Test if a recipe ingredient can be updated
     */
    @Test
    void testUpdateRecipeIngredient() {
        // Create a new recipe ingredient instance
        RecipeIngredient newRecipeIngredient = new RecipeIngredient();

        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientRepository.save(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeRepository.save(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientRepository.save(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Update the quantity of the recipe ingredient
        newRecipeIngredient.setQuantity("2 cups");

        // Save the updated recipe ingredient in the database
        recipeIngredientRepository.save(newRecipeIngredient);

        // Check if the recipe ingredient in the database is the same as the updated recipe ingredient
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).get()).isEqualTo(newRecipeIngredient);
    }

    /**
     * Test if all recipe ingredients can be deleted
     */
    @Test
    void testDeleteAllRecipeIngredients() {
        // Create a new recipe ingredient instance
        RecipeIngredient newRecipeIngredient = new RecipeIngredient();
        RecipeIngredient newRecipeIngredient2 = new RecipeIngredient();

        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientRepository.save(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeRepository.save(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        newRecipeIngredient2.setQuantity("1 cup");
        newRecipeIngredient2.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientRepository.save(newRecipeIngredient);
        recipeIngredientRepository.save(newRecipeIngredient2);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isTrue();
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient2.getId()).isPresent()).isTrue();

        // Delete all recipe ingredients from the database
        recipeIngredientRepository.deleteAll();

        // Check if the recipe ingredient is not present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isFalse();
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient2.getId()).isPresent()).isFalse();
    }

    /**
     * Test if all recipe ingredients can be find
     */
    @Test
    void testFindAllRecipeIngredients() {
        // Create a new recipe ingredient instance
        RecipeIngredient newRecipeIngredient = new RecipeIngredient();

        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientRepository.save(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeRepository.save(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Delete all recipe ingredients from the database
        recipeIngredientRepository.deleteAll();

        // Save the new recipe ingredient in the database
        recipeIngredientRepository.save(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientRepository.findById(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Check if all recipe ingredients can be found
        assertThat(StreamSupport.stream(recipeIngredientRepository.findAll().spliterator(), false).findAny().isEmpty()).isFalse();
    }


}
