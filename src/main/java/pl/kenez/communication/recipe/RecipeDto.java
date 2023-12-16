package pl.kenez.communication.recipe;

import java.io.Serializable;
import java.util.List;

public class RecipeDto implements Serializable {

    private String name;
    private Integer portions;

    private String preparation;

    private List<IngredientDto> ingredients;

    public RecipeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public RecipeDto name(String name) {
        this.name = name;
        return this;
    }

    public RecipeDto portions(Integer portions) {
        this.portions = portions;
        return this;
    }

    public RecipeDto preparation(String preparation) {
        this.preparation = preparation;
        return this;
    }

    public RecipeDto ingredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
