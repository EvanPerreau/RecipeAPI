package fr.evanperreau.recipeapi.repositoryTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.Ingredient;
import fr.evanperreau.recipeapi.repository.IngredientRepository;
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
public class IngredientRepositoryTest {

    @Autowired
    // IngredientRepository is used to interact with the Ingredient table in the database
    private IngredientRepository ingredientRepository;

    /**
     * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(ingredientRepository).isNotNull();
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
        ingredientRepository.save(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isTrue();

        // Check if the ingredient in the database is the same as the new ingredient
        assertThat(ingredientRepository.findById(newIngredient.getId()).get()).isEqualTo(newIngredient);
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
        ingredientRepository.save(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isTrue();

        // Delete the ingredient from the database
        ingredientRepository.delete(newIngredient);

        // Check if the ingredient is not present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isFalse();
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
        ingredientRepository.save(newIngredient);
        ingredientRepository.save(newIngredient2);

        // Check if the ingredient is present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isTrue();
        assertThat(ingredientRepository.findById(newIngredient2.getId()).isPresent()).isTrue();

        // Delete all ingredients from the database
        ingredientRepository.deleteAll();

        // Check if the ingredient is not present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isFalse();
        assertThat(ingredientRepository.findById(newIngredient2.getId()).isPresent()).isFalse();
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
        ingredientRepository.save(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isTrue();

        // Update the properties of the ingredient
        newIngredient.setName("Updated Ingredient");

        // Save the updated ingredient in the database
        ingredientRepository.save(newIngredient);

        // Check if the updated ingredient is present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isTrue();

        // Check if the updated ingredient in the database is the same as the new ingredient
        assertThat(ingredientRepository.findById(newIngredient.getId()).get()).isEqualTo(newIngredient);
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
        ingredientRepository.deleteAll();

        // Save the new ingredient in the database
        ingredientRepository.save(newIngredient);

        // Check if the ingredient is present in the database
        assertThat(ingredientRepository.findById(newIngredient.getId()).isPresent()).isTrue();

        // Check if all ingredients can be found
        assertThat(StreamSupport.stream(ingredientRepository.findAll().spliterator(), false).findAny().isEmpty()).isFalse();
    }
}
