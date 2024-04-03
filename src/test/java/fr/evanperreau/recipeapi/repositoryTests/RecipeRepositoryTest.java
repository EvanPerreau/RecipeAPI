package fr.evanperreau.recipeapi.repositoryTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.DifficultyLevel;
import fr.evanperreau.recipeapi.model.Recipe;
import fr.evanperreau.recipeapi.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest()
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeRepositoryTest {

    @Autowired
    // RecipeRepository is used to interact with the Recipe table in the database
    private RecipeRepository recipeRepository;



    /**
    * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(recipeRepository).isNotNull();
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
        recipeRepository.save(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isTrue();

        // Check if the recipe in the database is the same as the new recipe
        assertThat(recipeRepository.findById(newRecipe.getId()).get()).isEqualTo(newRecipe);
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
        recipeRepository.save(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isTrue();

        // Delete the recipe from the database
        recipeRepository.delete(newRecipe);

        // Check if the recipe is not present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isFalse();
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
        recipeRepository.save(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isTrue();

        // Update the recipe in the database
        newRecipe.setName("Updated Test Recipe");
        recipeRepository.save(newRecipe);

        // Check if the recipe in the database is the same as the updated recipe
        assertThat(recipeRepository.findById(newRecipe.getId()).get()).isEqualTo(newRecipe);
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
        recipeRepository.save(newRecipe);
        recipeRepository.save(newRecipe2);

        // Check if the recipe is present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isTrue();
        assertThat(recipeRepository.findById(newRecipe2.getId()).isPresent()).isTrue();

        // Delete all recipes from the database
        recipeRepository.deleteAll();

        // Check if the recipe is not present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isFalse();
        assertThat(recipeRepository.findById(newRecipe2.getId()).isPresent()).isFalse();
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
        recipeRepository.deleteAll();

        // Save the new recipe in the database
        recipeRepository.save(newRecipe);

        // Check if the recipe is present in the database
        assertThat(recipeRepository.findById(newRecipe.getId()).isPresent()).isTrue();

        // Check if the recipe table is not empty
        assertThat(StreamSupport.stream(recipeRepository.findAll().spliterator(), false).findAny().isEmpty()).isFalse();
    }
}
