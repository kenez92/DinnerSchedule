package pl.kenez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.service.RecipeService;

@RestController
@RequestMapping("api/recipe")
public class RecipeController {
    private final RecipeService service;

    @Autowired
    public RecipeController(final RecipeService service) {
        this.service = service;
    }

    @PostMapping
    void save(@RequestBody final RecipeDto recipe) {
        service.addRecipe(recipe);
    }
}
