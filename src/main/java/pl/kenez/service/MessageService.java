package pl.kenez.service;

import pl.kenez.communication.recipe.RecipeDto;

import java.util.Set;

public interface MessageService {
    String prepareMessage(final Set<RecipeDto> recipes);
}
