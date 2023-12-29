package pl.kenez.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kenez.db.model.Recipe;

import java.util.Set;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM recipe r ORDER BY RANDOM() LIMIT ?1")
    Set<Recipe> findRandomRecipes(final int quantity);
}