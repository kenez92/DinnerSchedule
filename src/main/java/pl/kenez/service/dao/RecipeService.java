package pl.kenez.service.dao;

import org.springframework.stereotype.Service;
import pl.kenez.communication.admin.UpdateRecipeDto;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.repository.RecipeRepository;
import pl.kenez.mapper.RecipeMapper;

import java.util.List;
import java.util.Set;

@Service
public class RecipeService {
    private final RecipeMapper mapper;
    private final RecipeRepository repository;

    public RecipeService(final RecipeMapper mapper, final RecipeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void addRecipe(final RecipeDto recipeDto) {
        repository.save(mapper.mapToRecipe(recipeDto));
    }

    public Set<RecipeDto> findRandomRecipes(final int quantity) {
        return mapper.mapToRecipeDtos(repository.findRandomRecipes(quantity));
    }

    public void updateDatabase(final List<UpdateRecipeDto> recipes) {
        repository.deleteAll();
        repository.saveAll(mapper.mapToRecipes(recipes));
    }
}
