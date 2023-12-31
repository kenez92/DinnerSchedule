package pl.kenez.service.admin;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kenez.db.model.Recipe;
import pl.kenez.service.dao.RecipeService;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final RecipeService recipeService;

    @Value("${recipes.file.location}")
    private String fileLocation;

    @Autowired
    public AdminService(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public void updateFromCsvFile() {
        recipeService.updateDatabase(getRecipesFromExcelFile());
    }

    private Set<Recipe> getRecipesFromExcelFile() {
        final List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileLocation));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        records.removeFirst(); // Remove column names
        return records.stream()
                      .map(e -> new Recipe().name(e.get(1))
                                            .ingredients(e.get(2))
                                            .preparation(e.get(3))
                                            .portions(Integer.valueOf(e.get(4))))
                      .collect(Collectors.toSet());
    }
}
