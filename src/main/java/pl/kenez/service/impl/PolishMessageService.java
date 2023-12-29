package pl.kenez.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.service.MessageService;
import pl.kenez.service.PrepareBuyListService;

import java.util.List;
import java.util.Set;

@Service
class PolishMessageService implements MessageService {
    private static final String ENTER = "\n";
    private static final String SPACE = " ";

    final PrepareBuyListService prepareBuyListService;

    @Autowired
    PolishMessageService(final PrepareBuyListService prepareBuyListService) {
        this.prepareBuyListService = prepareBuyListService;
    }

    @Override
    public String prepareMessage(final Set<RecipeDto> recipes) {
        return toContent(recipes, prepareBuyListService.prepareBuyList(recipes));
    }

    private String toContent(final Set<RecipeDto> recipes, final List<IngredientDto> buyList) {
        final StringBuilder content = new StringBuilder();
        recipes.forEach(e -> content.append(recipeToString(e))
                                    .append(ENTER)
                                    .append(ENTER));
        content.append(buyListToString(buyList));
        return content.toString();
    }

    private String buyListToString(final List<IngredientDto> buyList) {
        final StringBuilder sb = new StringBuilder();
        sb.append(ENTER)
          .append("Lista zakupów: ")
          .append(ENTER);
        buyList.forEach(e -> sb.append(e.getName())
                               .append(SPACE)
                               .append(e.getAmount())
                               .append(SPACE)
                               .append(e.getUnit().getName())
                               .append(ENTER));
        return sb.toString();
    }

    private String recipeToString(final RecipeDto recipe) {
        final StringBuilder sb = new StringBuilder();
        sb.append(recipe.getName())
          .append(ENTER)
          .append("Składniki: ");
        recipe.getIngredients().forEach(e -> sb.append(e.getName())
                                               .append(SPACE)
                                               .append(e.getAmount())
                                               .append(SPACE)
                                               .append(e.getUnit().getName())
                                               .append(ENTER));
        sb.append("Porcje: ")
          .append(recipe.getPortions())
          .append(ENTER)
          .append(recipe.getPreparation());
        return sb.toString();
    }
}