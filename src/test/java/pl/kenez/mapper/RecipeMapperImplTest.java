package pl.kenez.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;
import pl.kenez.enums.Unit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeMapperImplTest {

    private RecipeMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new RecipeMapperImpl();
    }

    @Test
    void shouldMapToRecipe() {
        final Recipe result = mapper.mapToRecipe(
                new RecipeDto().name("Pierś w cebuli")
                               .portions(2)
                               .preparation("Pierś pokroic na duże kawałki, zamarynować w przyprawach i odstawić na " +
                                       "pare godzin. Podsmażyć pierś na patalni, a następnie zeszklić cebulkę. Dodać " +
                                       "śmietanę i dusić przez 60 min na małym ogniu. Pod koniec dorzucić pokrojone " +
                                       "pomidorki koktajlowe. Podawać z pitą, bułką na parze lub innym pieczywem.")
                               .ingredients(List.of(
                                       new IngredientDto().name("pierś z kurczaka")
                                                          .unit(Unit.PIECE)
                                                          .amount(1d),
                                       new IngredientDto().name("cebula")
                                                          .unit(Unit.PIECE)
                                                          .amount(5d),
                                       new IngredientDto().name("śmietana 30%")
                                                          .unit(Unit.PIECE)
                                                          .amount(1d),
                                       new IngredientDto().name("pita")
                                                          .unit(Unit.PIECE)
                                                          .amount(2d),
                                       new IngredientDto().name("pomidorki koktajlowe")
                                                          .unit(Unit.GRAM)
                                                          .amount(300d))));

        assertThat(result).usingRecursiveComparison()
                          .isEqualTo(new Recipe().name("Pierś w cebuli")
                                                 .portions(2)
                                                 .preparation("Pierś pokroic na duże kawałki, zamarynować w " +
                                                         "przyprawach i odstawić na pare godzin. Podsmażyć pierś " +
                                                         "na patalni, a następnie zeszklić cebulkę. Dodać śmietanę " +
                                                         "i dusić przez 60 min na małym ogniu. Pod koniec dorzucić " +
                                                         "pokrojone pomidorki koktajlowe. Podawać z pitą, bułką na " +
                                                         "parze lub innym pieczywem.")
                                                 .ingredients("pierś z kurczaka,1.0,szt;cebula,5.0,szt;śmietana 30%," +
                                                         "1.0,szt;pita,2.0,szt;pomidorki koktajlowe,300.0,gram"));
    }
}