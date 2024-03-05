package com.surjeet.recipesharingapp.repository;

import com.surjeet.recipesharingapp.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
