package pl.kenez.service.admin;

import com.poiji.bind.Poiji;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kenez.communication.admin.UpdateRecipeDto;
import pl.kenez.service.dao.RecipeService;

import java.io.File;
import java.util.List;

@Service
public class AdminService {
    private final RecipeService recipeService;
    @Value("${recipes.file.location}")
    private String fileLocation;

    public AdminService(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public void updateFromExcel() {
        recipeService.updateDatabase(getRecipesFromExcelFile());
    }

    private List<UpdateRecipeDto> getRecipesFromExcelFile() {
//        return Poiji.fromExcel(new File("src\\main\\resources/recipes.xlsx"), UpdateRecipeDto.class);
        return Poiji.fromExcel(new File(fileLocation), UpdateRecipeDto.class);
    }
}
