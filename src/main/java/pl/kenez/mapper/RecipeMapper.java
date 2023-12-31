package pl.kenez.mapper;

import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;

import java.util.Set;

public interface RecipeMapper {
    Recipe mapToRecipe(final RecipeDto recipe);

    Set<RecipeDto> mapToRecipeDtos(final Set<Recipe> randomRecipes);
}
