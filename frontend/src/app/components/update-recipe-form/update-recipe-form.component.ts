import { Component, Inject } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    MatRadioModule,
  ],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.scss',
})
export class UpdateRecipeFormComponent {
  recipeItem: any = {
    title: 'Pizza',
    description: 'Nice food',
    foodType: 'veg',
    image: 'test.jpg',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private recipeService: RecipeService
  ) {}

  onSubmit() {
    console.log('values', this.recipeItem);
  }
}
