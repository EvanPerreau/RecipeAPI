package fr.evanperreau.recipeapi.serviceTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.Ingredient;
import fr.evanperreau.recipeapi.service.IngredientService;
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
public class IngredientServiceTest {

    @Autowired
    // IngredientService is used to interact with the Ingredient table in the database
    private IngredientService ingredientService;

    /**
     * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(ingredientService).isNotNull();
    }

    /**
     * Test if an ingredient can be saved
     */
    @Test
    void testSaveIngredient() {
        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();

        // Set the properties of the new ingredient
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isTrue();

        // Check if the ingredient in the database is the same as the new ingredient
        assertThat(ingredientService.getIngredient(newIngredient.getId()).get()).isEqualTo(newIngredient);
    }

    /**
     * Test if an ingredient can be deleted
     */
    @Test
    void testDeleteIngredient() {
        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();

        // Set the properties of the new ingredient
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isTrue();

        // Delete the ingredient from the database
        ingredientService.deleteIngredient(newIngredient.getId());

        // Check if the ingredient is not present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isFalse();
    }

    /**
     * Test deleting all ingredients
     */
    @Test
    void testDeleteAllIngredients() {
        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();
        Ingredient newIngredient2 = new Ingredient();

        // Set the properties of the new ingredient
        newIngredient.setName("Test Ingredient");
        newIngredient2.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientService.saveOrUpdateIngredient(newIngredient);
        ingredientService.saveOrUpdateIngredient(newIngredient2);

        // Check if the ingredient is present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isTrue();
        assertThat(ingredientService.getIngredient(newIngredient2.getId()).isPresent()).isTrue();

        // Delete all ingredients from the database
        ingredientService.deleteIngredients();

        // Check if the ingredient is not present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isFalse();
        assertThat(ingredientService.getIngredient(newIngredient2.getId()).isPresent()).isFalse();
    }

    /**
     * Test if an ingredient can be updated
     */
    @Test
    void testUpdateIngredient() {
        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();

        // Set the properties of the new ingredient
        newIngredient.setName("Test Ingredient");

        // Save the new ingredient in the database
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isTrue();

        // Update the properties of the ingredient
        newIngredient.setName("Updated Ingredient");

        // Save the updated ingredient in the database
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Check if the updated ingredient is present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isTrue();

        // Check if the updated ingredient in the database is the same as the new ingredient
        assertThat(ingredientService.getIngredient(newIngredient.getId()).get()).isEqualTo(newIngredient);
    }

    /**
     * Test if all ingredients can be found
     */
    @Test
    void testFindAllIngredients() {
        // Create a new ingredient instance
        Ingredient newIngredient = new Ingredient();

        // Set the properties of the new ingredient
        newIngredient.setName("Test Ingredient");

        // Delete all ingredients from the database
        ingredientService.deleteIngredients();

        // Save the new ingredient in the database
        ingredientService.saveOrUpdateIngredient(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientService.getIngredient(newIngredient.getId()).isPresent()).isTrue();

        // Check if all ingredients can be found
        assertThat(StreamSupport.stream(ingredientService.getIngredients().spliterator(), false).findAny().isEmpty()).isFalse();
    }
}
