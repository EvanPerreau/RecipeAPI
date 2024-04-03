package fr.evanperreau.recipeapi;

import fr.evanperreau.recipeapi.model.DifficultyLevel;
import fr.evanperreau.recipeapi.model.Recipe;
import fr.evanperreau.recipeapi.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RecipeApiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started.");
    }
}
