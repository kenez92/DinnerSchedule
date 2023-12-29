package pl.kenez.service;

import org.springframework.stereotype.Service;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PrepareBuyListService {

    public List<IngredientDto> prepareBuyList(final Set<RecipeDto> recipes) {
        final List<IngredientDto> buyList = new ArrayList<>();
        recipes.stream()
               .flatMap(e -> e.getIngredients().stream())
               .toList()
               .forEach(e -> addToList(buyList, e.copy()));
        return buyList;
    }

    private void addToList(final List<IngredientDto> buyList, final IngredientDto ingredient) {
        if (buyList.contains(ingredient)) {
            addAmountToIngredient(findIngredientFromList(buyList, ingredient), ingredient.getAmount());
        } else {
            buyList.add(ingredient);
        }
    }

    private IngredientDto findIngredientFromList(final List<IngredientDto> ingredients, final IngredientDto ingredient) {
        return ingredients.stream()
                          .filter(e -> e.getName().equals(ingredient.getName())
                                  && e.getUnit().equals(ingredient.getUnit()))
                          .findFirst()
                          .orElseThrow(() -> new IllegalArgumentException("Something went wrong!"));
    }

    private void addAmountToIngredient(final IngredientDto ingredient, final Double amount) {
        ingredient.setAmount(ingredient.getAmount() + amount);
    }
}
