package fr.evanperreau.recipeapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipeIngredient")
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    @Column(name = "quantity")
    private String quantity;
}