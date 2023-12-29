package pl.kenez.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kenez.communication.recipe.RecipeDto;
import pl.kenez.service.dao.RecipeService;

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
        return "api/addRecipe";
    }

    @GetMapping
    public String index() {
        return "api/addRecipe";
    }
}
