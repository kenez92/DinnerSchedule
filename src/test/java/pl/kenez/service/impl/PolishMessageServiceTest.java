package pl.kenez.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.enums.Unit;
import pl.kenez.service.MessageService;
import pl.kenez.service.PrepareBuyListService;
import pl.kenez.db.dao.RecipeService;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PolishMessageServiceTest {
    @Mock
    private RecipeService recipeService;

    private MessageService service;

    @BeforeEach
    void setUp() {
        service = new PolishMessageService(recipeService, new PrepareBuyListService());
    }

    @Test
    void shouldReturnCorrectMessage() {
        when(recipeService.findRandomRecipes(anyInt())).thenReturn(Set.of(
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

        final String result = service.prepareMessage();

        assertThat(result).isEqualTo("name1\n" +
                "Składniki: ingredient1 1.0 szt\n" +
                "ingredient2 8.0 szt\n" +
                "ingredient3 3.0 kg\n" +
                "ingredient4 7.0 gram\n" +
                "ingredient5 300.0 gram\n" +
                "Porcje: 2\n" +
                "Preparation\n" +
                "\n" +
                "name2\n" +
                "Składniki: ingredient1 2.0 gram\n" +
                "ingredient2 3.0 szt\n" +
                "ingredient3 1.0 kg\n" +
                "ingredient4 5.0 gram\n" +
                "ingredient6 311.0 gram\n" +
                "Porcje: 2\n" +
                "Preparation2\n" +
                "\n" +
                "\n" +
                "Lista zakupów: \n" +
                "ingredient1 1.0 szt\n" +
                "ingredient2 8.0 szt\n" +
                "ingredient3 3.0 kg\n" +
                "ingredient4 7.0 gram\n" +
                "ingredient5 300.0 gram\n" +
                "ingredient1 2.0 gram\n" +
                "ingredient6 311.0 gram\n");
    }

}