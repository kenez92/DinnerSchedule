package pl.kenez.mapper.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;
import pl.kenez.enums.Unit;
import pl.kenez.mapper.RecipeMapper;

import java.util.List;
import java.util.Set;

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
                                       new IngredientDto().name("Pierś z kurczaka")
                                                          .unit(Unit.SZTUKA)
                                                          .amount(1d),
                                       new IngredientDto().name("Cebula")
                                                          .unit(Unit.SZTUKA)
                                                          .amount(5d),
                                       new IngredientDto().name("śmiETANA 30%")
                                                          .unit(Unit.SZTUKA)
                                                          .amount(1d),
                                       new IngredientDto().name("pita")
                                                          .unit(Unit.SZTUKA)
                                                          .amount(2d),
                                       new IngredientDto().name("Pomidorki Koktajlowe")
                                                          .unit(Unit.GRAM)
                                                          .amount(300d))));

        assertThat(result).usingRecursiveComparison()
                          .isEqualTo(new Recipe().name("Piers w cebuli")
                                                 .portions(2)
                                                 .preparation("Piers pokroic na duze kawalki, zamarynowac w " +
                                                         "przyprawach i odstawic na pare godzin. Podsmazyc piers na " +
                                                         "patalni, a nastepnie zeszklic cebulke. Dodac smietane " +
                                                         "i dusic przez 60 min na malym ogniu. Pod koniec dorzucic" +
                                                         " pokrojone pomidorki koktajlowe. Podawac z pita, bulka " +
                                                         "na parze lub innym pieczywem.")
                                                 .ingredients("piers z kurczaka,1.0,sztuka;cebula,5.0,sztuka;smietana 30%," +
                                                         "1.0,sztuka;pita,2.0,sztuka;pomidorki koktajlowe,300.0,gram"));
    }

    @Test
    void shouldMapToIngredientDtos() {
        final Set<RecipeDto> result = mapper.mapToRecipeDtos(Set.of(
                new Recipe().name("Piers w cebuli")
                            .portions(2)
                            .preparation("Piers pokroic na duze kawalki, zamarynowac w " +
                                    "przyprawach i odstawic na pare godzin. Podsmazyc piers na " +
                                    "patalni, a nastepnie zeszklic cebulke. Dodac smietane " +
                                    "i dusic przez 60 min na malym ogniu. Pod koniec dorzucic" +
                                    " pokrojone pomidorki koktajlowe. Podawac z pita, bulka " +
                                    "na parze lub innym pieczywem.")
                            .ingredients("piers z kurczaka,1.0,sztuka;cebula,5.0,sztuka;smietana 30%," +
                                    "1.0,sztuka;pita,2.0,sztuka;pomidorki koktajlowe,300.0,gram"),
                new Recipe().name("Jajka z ziemniakami")
                            .portions(2)
                            .preparation("Ziemniaki ugotowac. Jajka usmazyc. Podawac z surowka.")
                            .ingredients("ziemniaki,1.0,gram;jajka,3.0,sztuka;surowka,1.0,sztuka")));

        assertThat(result).usingRecursiveFieldByFieldElementComparator()
                          .containsExactlyInAnyOrder(
                                  new RecipeDto().name("Piers w cebuli")
                                                 .portions(2)
                                                 .preparation("Piers pokroic na duze kawalki, zamarynowac w " +
                                                         "przyprawach i odstawic na pare godzin. Podsmazyc piers na " +
                                                         "patalni, a nastepnie zeszklic cebulke. Dodac smietane " +
                                                         "i dusic przez 60 min na malym ogniu. Pod koniec dorzucic" +
                                                         " pokrojone pomidorki koktajlowe. Podawac z pita, bulka " +
                                                         "na parze lub innym pieczywem.")
                                                 .ingredients(List.of(
                                                         new IngredientDto().name("piers z kurczaka")
                                                                            .unit(Unit.SZTUKA)
                                                                            .amount(1d),
                                                         new IngredientDto().name("cebula")
                                                                            .unit(Unit.SZTUKA)
                                                                            .amount(5d),
                                                         new IngredientDto().name("smietana 30%")
                                                                            .unit(Unit.SZTUKA)
                                                                            .amount(1d),
                                                         new IngredientDto().name("pita")
                                                                            .unit(Unit.SZTUKA)
                                                                            .amount(2d),
                                                         new IngredientDto().name("pomidorki koktajlowe")
                                                                            .unit(Unit.GRAM)
                                                                            .amount(300d))),
                                  new RecipeDto().name("Jajka z ziemniakami")
                                                 .portions(2)
                                                 .preparation("Ziemniaki ugotowac. Jajka usmazyc. Podawac z surowka.")
                                                 .ingredients(List.of(
                                                         new IngredientDto().name("ziemniaki")
                                                                            .amount(1d)
                                                                            .unit(Unit.GRAM),
                                                         new IngredientDto().name("jajka")
                                                                            .amount(3d)
                                                                            .unit(Unit.SZTUKA),
                                                         new IngredientDto().name("surowka")
                                                                            .amount(1d)
                                                                            .unit(Unit.SZTUKA))));
    }
}