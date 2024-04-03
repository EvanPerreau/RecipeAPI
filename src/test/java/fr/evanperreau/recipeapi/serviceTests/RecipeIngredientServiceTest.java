package fr.evanperreau.recipeapi.serviceTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.*;
import fr.evanperreau.recipeapi.service.IngredientService;
import fr.evanperreau.recipeapi.service.RecipeIngredientService;
import fr.evanperreau.recipeapi.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeIngredientServiceTest {

    @Autowired
    // RecipeIngredientService is used to interact with the RecipeIngredient table in the database
    private RecipeIngredientService recipeIngredientService;
    @Autowired
    // Recipe service is used to interact with the Recipe table in the database
    private RecipeService recipeService;
    @Autowired
    // Ingredient service is used to interact with the Ingredient table in the database
    private IngredientService ingredientService;

    /**
     * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(recipeIngredientService).isNotNull();
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
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Check if the recipe ingredient in the database is the same as the new recipe ingredient
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).get()).isEqualTo(newRecipeIngredient);
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
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Delete the recipe ingredient from the database
        recipeIngredientService.deleteRecipeIngredient(newRecipeIngredient.getId());

        // Check if the recipe ingredient is not present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isFalse();
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
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Update the quantity of the recipe ingredient
        newRecipeIngredient.setQuantity("2 cups");

        // Save the updated recipe ingredient in the database
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient);

        // Check if the recipe ingredient in the database is the same as the updated recipe ingredient
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).get()).isEqualTo(newRecipeIngredient);
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
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        newRecipeIngredient2.setQuantity("1 cup");
        newRecipeIngredient2.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Save the new recipe ingredient in the database
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient);
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient2);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isTrue();
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient2.getId()).isPresent()).isTrue();

        // Delete all recipe ingredients from the database
        recipeIngredientService.deleteRecipesIngredients();

        // Check if the recipe ingredient is not present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isFalse();
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient2.getId()).isPresent()).isFalse();
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
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Set the properties of the new recipe ingredient
        newRecipeIngredient.setQuantity("1 cup");
        newRecipeIngredient.setId(new RecipeIngredientId(newRecipe.getId(), newIngredient.getId()));

        // Delete all recipe ingredients from the database
        recipeIngredientService.deleteRecipesIngredients();

        // Save the new recipe ingredient in the database
        recipeIngredientService.saveOrUpdateRecipeIngredient(newRecipeIngredient);

        // Check if the recipe ingredient is present in the database
        assertThat(recipeIngredientService.getRecipeIngredient(newRecipeIngredient.getId()).isPresent()).isTrue();

        // Check if all recipe ingredients can be found
        assertThat(StreamSupport.stream(recipeIngredientService.getRecipeIngredients().spliterator(), false).findAny().isEmpty()).isFalse();
    }
}
