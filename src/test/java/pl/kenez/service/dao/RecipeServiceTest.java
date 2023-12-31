package pl.kenez.service.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kenez.communication.recipe.IngredientDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.model.Recipe;
import pl.kenez.db.repository.RecipeRepository;
import pl.kenez.enums.Unit;
import pl.kenez.mapper.RecipeMapper;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @Mock
    private RecipeRepository repository;
    @Mock
    private RecipeMapper mapper;

    private RecipeService service;

    @BeforeEach
    void setUp() {
        this.service = new RecipeService(mapper, repository);
    }

    @Test
    void shouldInvokeMapperWithCorrectArgsWhenAddRecipe() {
        final ArgumentCaptor<RecipeDto> captor = ArgumentCaptor.forClass(RecipeDto.class);

        service.addRecipe(new RecipeDto().name("name")
                                         .ingredients(List.of(new IngredientDto().name("ingredient1")
                                                                                 .unit(Unit.GRAM)
                                                                                 .amount(1d)))
                                         .portions(2)
                                         .preparation("preparation"));

        verify(mapper).mapToRecipe(captor.capture());
        assertThat(captor.getValue()).usingRecursiveComparison()
                                     .isEqualTo(new RecipeDto().name("name")
                                                               .ingredients(List.of(new IngredientDto().name("ingredient1")
                                                                                                       .unit(Unit.GRAM)
                                                                                                       .amount(1d)))
                                                               .portions(2)
                                                               .preparation("preparation"));
    }

    @Test
    void shouldInvokeRepositoryWithCorrectArgsWhenAddRecipe() {
        final ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
        when(mapper.mapToRecipe(any())).thenReturn(new Recipe().name("name")
                                                               .preparation("preparation")
                                                               .ingredients("ingredient,1.0,sztuka;ingredient2,2.0,gram")
                                                               .portions(2));

        service.addRecipe(new RecipeDto());

        verify(repository).save(captor.capture());
        assertThat(captor.getValue()).usingRecursiveComparison()
                                     .isEqualTo(new Recipe().name("name")
                                                            .preparation("preparation")
                                                            .ingredients("ingredient,1.0,sztuka;ingredient2,2.0,gram")
                                                            .portions(2));
    }

    @Test
    void shouldInvokeRepositoryWithCorrectQuantityWhenFindRandomRecipes() {
        service.findRandomRecipes(3);

        verify(repository).findRandomRecipes(3);
    }

    @Test
    void shouldInvokeMapperWithCorrectArgsWenFindRandomRecipes() {
        final ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
        when(repository.findRandomRecipes(anyInt())).thenReturn(Set.of(
                new Recipe().name("name1")
                            .preparation("preparation1")
                            .ingredients("ingredient1,1.0,sztuka;ingredient2,2.0,gram")
                            .portions(2),
                new Recipe().name("name2")
                            .preparation("preparation2")
                            .ingredients("ingredient3,3.0,lyzka;ingredient4,3.0,szczypta")
                            .portions(3)));

        service.findRandomRecipes(3);

        verify(mapper).mapToRecipeDtos(captor.capture());
        assertThat(captor.getValue()).usingRecursiveFieldByFieldElementComparator()
                                     .containsExactlyInAnyOrder(
                                             new Recipe().name("name1")
                                                         .preparation("preparation1")
                                                         .ingredients("ingredient1,1.0,sztuka;ingredient2,2.0,gram")
                                                         .portions(2),
                                             new Recipe().name("name2")
                                                         .preparation("preparation2")
                                                         .ingredients("ingredient3,3.0,lyzka;ingredient4,3.0,szczypta")
                                                         .portions(3));
    }

    @Test
    void shouldInvokeDeleteAllFromRepositoryWhenUpdateDatabase() {
        service.updateDatabase(Set.of());

        verify(repository).deleteAll();
    }

    @Test
    void shouldInvokeSaveAllFromRepositoryWithCorrectArgWhenUpdateDatabase() {
        final ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
        service.updateDatabase(Set.of(
                new Recipe().name("name1")
                            .preparation("preparation1")
                            .ingredients("ingredient1,1.0,sztuka;ingredient2,2.0,gram")
                            .portions(2),
                new Recipe().name("name2")
                            .preparation("preparation2")
                            .ingredients("ingredient3,3.0,lyzka;ingredient4,3.0,szczypta")
                            .portions(3)));

        verify(repository).saveAll(captor.capture());
        assertThat(captor.getValue()).usingRecursiveFieldByFieldElementComparator()
                                     .containsExactlyInAnyOrder(
                                             new Recipe().name("name1")
                                                         .preparation("preparation1")
                                                         .ingredients("ingredient1,1.0,sztuka;ingredient2,2.0,gram")
                                                         .portions(2),
                                             new Recipe().name("name2")
                                                         .preparation("preparation2")
                                                         .ingredients("ingredient3,3.0,lyzka;ingredient4,3.0,szczypta")
                                                         .portions(3));
    }
}