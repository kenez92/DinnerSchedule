package pl.kenez.mapper;

import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;

public interface RecipeMapper {
    Recipe mapToRecipe(final RecipeDto recipe);
}
