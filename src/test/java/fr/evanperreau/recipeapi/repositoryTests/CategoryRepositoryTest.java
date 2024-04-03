package fr.evanperreau.recipeapi.repositoryTests;

import fr.evanperreau.recipeapi.AbstractIntegrationTest;
import fr.evanperreau.recipeapi.model.Category;
import fr.evanperreau.recipeapi.repository.CategoryRepository;
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
public class CategoryRepositoryTest {

    @Autowired
    // CategoryRepository is used to interact with the Category table in the database
    private CategoryRepository categoryRepository;

    /**
     * Test if the injected components are not null
     */
    @Test
    void injectedComponentsAreNotNull() {
        assertThat(categoryRepository).isNotNull();
    }

    /**
     * Test if a category can be saved
     */
    @Test
    void testSaveCategory() {
        // Create a new category instance
        Category newCategory = new Category();

        // Set the properties of the new category
        newCategory.setName("Test Category");

        // Save the new category in the database
        categoryRepository.save(newCategory);

        // Check if the category is present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isTrue();

        // Check if the category in the database is the same as the new category
        assertThat(categoryRepository.findById(newCategory.getId()).get()).isEqualTo(newCategory);
    }

    /**
     * Test if a category can be deleted
     */
    @Test
    void testDeleteCategory() {
        // Create a new category instance
        Category newCategory = new Category();

        // Set the properties of the new category
        newCategory.setName("Test Category");

        // Save the new category in the database
        categoryRepository.save(newCategory);

        // Check if the category is present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isTrue();

        // Delete the category from the database
        categoryRepository.delete(newCategory);

        // Check if the category is not present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isFalse();
    }

    /**
     * Test if a category can be updated
     */
    @Test
    void testUpdateCategory() {
        // Create a new category instance
        Category newCategory = new Category();

        // Set the properties of the new category
        newCategory.setName("Test Category");

        // Save the new category in the database
        categoryRepository.save(newCategory);

        // Check if the category is present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isTrue();

        // Update the name of the category
        newCategory.setName("Updated Category");

        // Save the updated category in the database
        categoryRepository.save(newCategory);

        // Check if the updated category is present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isTrue();

        // Check if the updated category in the database is the same as the new category
        assertThat(categoryRepository.findById(newCategory.getId()).get()).isEqualTo(newCategory);
    }

    /**
     * Test if all category can be deleted
     */
    @Test
    void testDeleteAllCategory() {
        // Create a new category instance
        Category newCategory = new Category();
        Category newCategory2 = new Category();

        // Set the properties of the new category
        newCategory.setName("Test Category");
        newCategory2.setName("Test Category 2");

        // Save the new category in the database
        categoryRepository.save(newCategory);
        categoryRepository.save(newCategory2);

        // Check if the category is present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isTrue();
        assertThat(categoryRepository.findById(newCategory2.getId()).isPresent()).isTrue();

        // Delete all categories from the database
        categoryRepository.deleteAll();

        // Check if the category is not present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isFalse();
        assertThat(categoryRepository.findById(newCategory2.getId()).isPresent()).isFalse();
    }

    /**
     * Test if all category can be find
     */
    @Test
    void testFindAllCategory() {
        // Create a new category instance
        Category newCategory = new Category();

        // Set the properties of the new category
        newCategory.setName("Test Category");

        // Delete all categories from the database
        categoryRepository.deleteAll();

        // Save the new category in the database
        categoryRepository.save(newCategory);

        // Check if the category is present in the database
        assertThat(categoryRepository.findById(newCategory.getId()).isPresent()).isTrue();

        // Check if the category in the database is the same as the new category
        assertThat(StreamSupport.stream(categoryRepository.findAll().spliterator(), false).findAny().isEmpty()).isFalse();
    }
}
