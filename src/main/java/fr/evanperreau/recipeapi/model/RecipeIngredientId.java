package fr.evanperreau.recipeapi.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RecipeIngredientId implements Serializable {

    private long recipeId;
    private long ingredientId;

    public RecipeIngredientId(long recipeId, long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    protected RecipeIngredientId() {

    }
}
