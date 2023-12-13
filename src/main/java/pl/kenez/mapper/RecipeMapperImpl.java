package pl.kenez.mapper;

import org.springframework.stereotype.Component;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
class RecipeMapperImpl implements RecipeMapper {
    private static final String INGREDIENT_DELIMITER = ",";
    private static final String INGREDIENTS_DELIMITER = ";";

    @Override
    public Recipe mapToRecipe(final RecipeDto recipeDto) {
        return new Recipe()
                .name(recipeDto.getName())
                .preparation(recipeDto.getPreparation())
                .portions(recipeDto.getPortions())
                .ingredients(mapToIngredients(recipeDto.getIngredients()));
    }

    private String mapToIngredients(final List<IngredientDto> ingredients) {
        return ingredients.stream()
                .map(this::mapToIngredient)
                .collect(Collectors.joining(INGREDIENTS_DELIMITER));
    }

    private String mapToIngredient(final IngredientDto ingredient) {
        return new StringJoiner(INGREDIENT_DELIMITER)
                .add(ingredient.getName())
                .add(String.valueOf(ingredient.getAmount()))
                .add(ingredient.getUnit().getName())
                .toString();
    }
}
