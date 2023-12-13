package pl.kenez.communication.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDto implements Serializable {

    @NotNull
    @Size(max = 128)
    private String name;
    @NotNull
    @Positive
    private Integer portions;

    @NotNull
    @Size(max = 2000)
    private String preparation;

    @NotNull
    @Size(max = 32)
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
