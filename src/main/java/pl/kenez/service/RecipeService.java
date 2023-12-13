package pl.kenez.service;

import org.springframework.stereotype.Service;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.db.repository.RecipeRepository;
import pl.kenez.mapper.RecipeMapper;

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
}
