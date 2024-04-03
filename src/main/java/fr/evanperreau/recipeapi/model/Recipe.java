package fr.evanperreau.recipeapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "cooking_time")
    private int cookingTime;

    @Column(name = "preparation_time")
    private int preparationTime;

    @Column(name = "servings")
    private int servings;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficulty;

    @Column(name = "category_id")
    private long categoryId;

}
