package pl.kenez.mapper;

import org.springframework.stereotype.Component;
import pl.kenez.communication.admin.UpdateRecipeDto;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;
import pl.kenez.enums.Unit;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static pl.kenez.utils.ReplacePolishCharactersUtils.replacePolishCharacters;

@Component
class RecipeMapperImpl implements RecipeMapper {
    private static final String INGREDIENT_DELIMITER = ",";
    private static final String INGREDIENTS_DELIMITER = ";";

    @Override
    public Recipe mapToRecipe(final RecipeDto recipeDto) {
        return new Recipe()
                .name(replacePolishCharacters(recipeDto.getName()))
                .preparation(replacePolishCharacters(recipeDto.getPreparation()))
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
                .add(replacePolishCharacters(ingredient.getName()).toLowerCase())
                .add(String.valueOf(ingredient.getAmount()))
                .add(ingredient.getUnit().getName())
                .toString();
    }

    @Override
    public Set<RecipeDto> mapToRecipeDtos(final Set<Recipe> recipes) {
        return recipes.stream()
                      .map(this::mapToRecipeDto)
                      .collect(Collectors.toSet());
    }

    private RecipeDto mapToRecipeDto(final Recipe recipe) {
        return new RecipeDto().name(recipe.getName())
                              .portions(recipe.getPortions())
                              .preparation(recipe.getPreparation())
                              .ingredients(mapToIngredientsDtos(recipe.getIngredients()));
    }

    private List<IngredientDto> mapToIngredientsDtos(final String ingredients) {
        return Arrays.asList(ingredients.split(INGREDIENTS_DELIMITER)).stream()
                     .map(this::mapToIngredientDto)
                     .collect(Collectors.toList());
    }

    private IngredientDto mapToIngredientDto(final String ingredient) {
        final String[] splitIngredient = ingredient.split(INGREDIENT_DELIMITER);
        return new IngredientDto().name(splitIngredient[0])
                                  .amount(Double.valueOf(splitIngredient[1]))
                                  .unit(Unit.fromName(splitIngredient[2]));
    }

    @Override
    public List<Recipe> mapToRecipes(final List<UpdateRecipeDto> recipes) {
        return recipes.stream()
                      .map(this::toRecipe)
                      .collect(Collectors.toList());
    }

    private Recipe toRecipe(final UpdateRecipeDto recipe) {
        return new Recipe().name(replacePolishCharacters(recipe.getName()))
                           .ingredients(replacePolishCharacters(recipe.getIngredients()).toLowerCase())
                           .portions(recipe.getPortions())
                           .preparation(replacePolishCharacters(recipe.getPreparations()));
    }
}
