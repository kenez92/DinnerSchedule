package pl.kenez.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.enums.Unit;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PrepareBuyListServiceTest {
    private PrepareBuyListService service;

    @BeforeEach
    void setUp() {
        this.service = new PrepareBuyListService();
    }

    @Test
    void shouldReturnCorrectBuyList() {
        final List<IngredientDto> result = service.prepareBuyList(Set.of(
                new RecipeDto().name("name1")
                               .portions(2)
                               .preparation("Preparation")
                               .ingredients(List.of(
                                       new IngredientDto().name("ingredient1")
                                                          .unit(Unit.PIECE)
                                                          .amount(1d),
                                       new IngredientDto().name("ingredient2")
                                                          .unit(Unit.PIECE)
                                                          .amount(5d),
                                       new IngredientDto().name("ingredient3")
                                                          .unit(Unit.KG)
                                                          .amount(2d),
                                       new IngredientDto().name("ingredient4")
                                                          .unit(Unit.GRAM)
                                                          .amount(2d),
                                       new IngredientDto().name("ingredient5")
                                                          .unit(Unit.GRAM)
                                                          .amount(300d))),
                new RecipeDto().name("name2")
                               .portions(2)
                               .preparation("Preparation2")
                               .ingredients(List.of(
                                       new IngredientDto().name("ingredient1")
                                                          .unit(Unit.GRAM)
                                                          .amount(2d),
                                       new IngredientDto().name("ingredient2")
                                                          .unit(Unit.PIECE)
                                                          .amount(3d),
                                       new IngredientDto().name("ingredient3")
                                                          .unit(Unit.KG)
                                                          .amount(1d),
                                       new IngredientDto().name("ingredient4")
                                                          .unit(Unit.GRAM)
                                                          .amount(5d),
                                       new IngredientDto().name("ingredient6")
                                                          .unit(Unit.GRAM)
                                                          .amount(311d)))));

        assertThat(result).usingRecursiveFieldByFieldElementComparator()
                          .containsExactlyInAnyOrder(
                                  new IngredientDto().name("ingredient1")
                                                     .unit(Unit.PIECE)
                                                     .amount(1d),
                                  new IngredientDto().name("ingredient1")
                                                     .unit(Unit.GRAM)
                                                     .amount(2d),
                                  new IngredientDto().name("ingredient2")
                                                     .unit(Unit.PIECE)
                                                     .amount(8d),
                                  new IngredientDto().name("ingredient3")
                                                     .unit(Unit.KG)
                                                     .amount(3d),
                                  new IngredientDto().name("ingredient4")
                                                     .unit(Unit.GRAM)
                                                     .amount(7d),
                                  new IngredientDto().name("ingredient5")
                                                     .unit(Unit.GRAM)
                                                     .amount(300d),
                                  new IngredientDto().name("ingredient6")
                                                     .unit(Unit.GRAM)
                                                     .amount(311d));
    }
}