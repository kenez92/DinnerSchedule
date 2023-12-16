package pl.kenez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.service.RecipeService;

@Controller
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService service;

    @Autowired
    public RecipeController(final RecipeService service) {
        this.service = service;
    }

    @PostMapping
    public String save(@ModelAttribute("recipeDto") final RecipeDto recipeDto) {
        service.addRecipe(recipeDto);
        return "recipe/addRecipe";

    }

    @GetMapping
    public String index() {
        return "recipe/addRecipe";
    }
}
