import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';
import { AuthService } from '../../services/auth.service';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RecipeCardComponent, MatIconModule, MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  recipes: any = [1, 1];

  constructor(
    public dialog: MatDialog,
    public authService: AuthService,
    private recipeService: RecipeService
  ) {}

  handleOpenCreateRecipeForm() {
    this.dialog.open(CreateRecipeFormComponent);
  }
  ngOnInit() {
    // this.authService.getUserProfile();
    this.recipeService.getRecipes().subscribe();
    this.recipeService.recipeSubject.subscribe(
      (state) => (this.recipes = state.recipes)
    );
  }
}
