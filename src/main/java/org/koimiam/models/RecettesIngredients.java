package org.koimiam.models;

import java.util.List;
import java.util.Map;

public class RecettesIngredients {
    private List<String> recettes;

    private Map<String, Ingredient> ingredients;

    public List<String> getRecettes() {
        return recettes;
    }

    public void setRecettes(List<String> recettes) {
        this.recettes = recettes;
    }

    public Map<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
