package fr.evanperreau.recipeapi.serviceTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.DifficultyLevel;
import fr.evanperreau.recipeapi.model.Recipe;
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
public class RecipeServiceTest {

    @Autowired
    // RecipeService is used to interact with the Recipe table in the database
    private RecipeService recipeService;

    /**
     * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(recipeService).isNotNull();
    }

    /**
     * Test if a recipe can be saved
     */
    @Test
    void testSaveRecipe() {
        // Create a new recipe instance
        Recipe newRecipe = new Recipe();

        // Set the properties of the new recipe
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isTrue();

        // Check if the recipe in the database is the same as the new recipe
        assertThat(recipeService.getRecipe(newRecipe.getId()).get()).isEqualTo(newRecipe);
    }

    /**
     Test if a recipe can be deleted
     */
    @Test
    void testDeleteRecipe() {
        // Create a new recipe instance
        Recipe newRecipe = new Recipe();

        // Set the properties of the new recipe
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isTrue();

        // Delete the recipe from the database
        recipeService.deleteRecipe(newRecipe.getId());

        // Check if the recipe is not present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isFalse();
    }

    /**
     * Test if a recipe can be updated
     */
    @Test
    void testUpdateRecipe() {
        // Create a new recipe instance
        Recipe newRecipe = new Recipe();

        // Set the properties of the new recipe
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isTrue();

        // Update the recipe in the database
        newRecipe.setName("Updated Test Recipe");
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Check if the recipe in the database is the same as the updated recipe
        assertThat(recipeService.getRecipe(newRecipe.getId()).get()).isEqualTo(newRecipe);
    }

    /**
     * Test delete all recipes
     */
    @Test
    void testDeleteAllRecipes() {
        // Create a new recipe instance
        Recipe newRecipe = new Recipe();
        Recipe newRecipe2 = new Recipe();

        // Set the properties of the new recipe
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);
        newRecipe2.setName("Test Recipe");
        newRecipe2.setInstructions("Test Instructions");
        newRecipe2.setDifficulty(DifficultyLevel.easy);
        newRecipe2.setPreparationTime(10);
        newRecipe2.setCookingTime(20);

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);
        recipeService.saveOrUpdateRecipe(newRecipe2);

        // Check if the recipe is present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isTrue();
        assertThat(recipeService.getRecipe(newRecipe2.getId()).isPresent()).isTrue();

        // Delete all recipes from the database
        recipeService.deleteAllRecipes();

        // Check if the recipe is not present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isFalse();
        assertThat(recipeService.getRecipe(newRecipe2.getId()).isPresent()).isFalse();
    }

    /**
     * Test if all recipes can be found
     */
    @Test
    void testFindAllRecipes() {
        // Create a new recipe instance
        Recipe newRecipe = new Recipe();

        // Set the properties of the new recipe
        newRecipe.setName("Test Recipe");
        newRecipe.setInstructions("Test Instructions");
        newRecipe.setDifficulty(DifficultyLevel.easy);
        newRecipe.setPreparationTime(10);
        newRecipe.setCookingTime(20);

        // Delete all recipes
        recipeService.deleteAllRecipes();

        // Save the new recipe in the database
        recipeService.saveOrUpdateRecipe(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeService.getRecipe(newRecipe.getId()).isPresent()).isTrue();

        // Check if the recipe table is not empty
        assertThat(StreamSupport.stream(recipeService.getRecipes().spliterator(), false).findAny().isEmpty()).isFalse();
    }
}
