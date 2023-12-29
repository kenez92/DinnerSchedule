package pl.kenez.communication.recipe;

import pl.kenez.enums.Unit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class IngredientDto implements Serializable {

    @NotEmpty
    private String name;
    @NotNull
    private Unit unit;
    @NotNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientDto that = (IngredientDto) o;

        if (!name.equals(that.name)) return false;
        return unit == that.unit;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + unit.hashCode();
        return result;
    }

    public IngredientDto copy() {
        return new IngredientDto().name(name)
                                  .unit(unit)
                                  .amount(amount);
    }
}
