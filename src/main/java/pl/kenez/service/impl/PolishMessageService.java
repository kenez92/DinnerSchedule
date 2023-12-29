package pl.kenez.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.enums.Unit;
import pl.kenez.service.MessageService;
import pl.kenez.service.PrepareBuyListService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class PolishMessageService implements MessageService {
    private static final String ENTER = "\n";
    private static final String SPACE = " ";
    private static final Set<Unit> SPICES = Set.of(Unit.ML, Unit.LYZECZKA, Unit.LYZKA, Unit.PRZYPRAWA, Unit.SZCZYPTA,
            Unit.ZABEK);

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
        buyList.stream()
               .filter(e -> !isSpice(e.getUnit()))
               .toList()
               .forEach(e -> sb.append(e.getName())
                               .append(SPACE)
                               .append(e.getAmount())
                               .append(SPACE)
                               .append(e.getUnit().name().toLowerCase())
                               .append(ENTER));
        sb.append(ENTER)
          .append("Rozważ przyprawy: ");
        buyList.stream()
               .filter(e -> isSpice(e.getUnit()))
               .map(IngredientDto::getName)
               .collect(Collectors.toCollection(LinkedHashSet::new))
               .forEach(e -> sb.append(ENTER)
                               .append(e));
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
                                               .append(e.getUnit().name().toLowerCase())
                                               .append(ENTER));
        sb.append("Porcje: ")
          .append(recipe.getPortions())
          .append(ENTER)
          .append(recipe.getPreparation());
        return sb.toString();
    }

    private boolean isSpice(final Unit unit) {
        return SPICES.contains(unit);
    }
}