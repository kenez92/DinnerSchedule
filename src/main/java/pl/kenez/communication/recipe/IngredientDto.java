package pl.kenez.communication.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.kenez.enums.Unit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class IngredientDto implements Serializable {

    private String name;

    private Unit unit;

    private Double amount;

    public IngredientDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public IngredientDto name(String name) {
        this.name = name;
        return this;
    }

    public IngredientDto unit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public IngredientDto amount(Double amount) {
        this.amount = amount;
        return this;
    }
}
