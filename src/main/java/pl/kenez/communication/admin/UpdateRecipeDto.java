package pl.kenez.communication.admin;

import com.poiji.annotation.ExcelCellName;

import java.io.Serializable;

public class UpdateRecipeDto implements Serializable {
    @ExcelCellName("Name")
    private String name;
    @ExcelCellName("Portions")
    private Integer portions;
    @ExcelCellName("Preparation")
    private String preparations;
    @ExcelCellName("Ingredients")
    private String ingredients;

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

    public String getPreparations() {
        return preparations;
    }

    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
