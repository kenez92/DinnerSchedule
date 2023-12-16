package pl.kenez.db.model;

import jakarta.persistence.*;

@Table
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer portions;
    private String ingredients;
    private String preparation;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(final String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(final String preparation) {
        this.preparation = preparation;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(final Integer portions) {
        this.portions = portions;
    }

    public Recipe name(final String name) {
        this.name = name;
        return this;
    }

    public Recipe ingredients(final String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Recipe preparation(final String preparation) {
        this.preparation = preparation;
        return this;
    }

    public Recipe portions(final Integer portions) {
        this.portions = portions;
        return this;
    }
}
