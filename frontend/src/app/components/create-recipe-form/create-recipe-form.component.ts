import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { RecipeService } from '../../services/recipe.service';

@Component({
  selector: 'app-create-recipe-form',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    MatRadioModule,
  ],
  templateUrl: './create-recipe-form.component.html',
  styleUrl: './create-recipe-form.component.scss',
})
export class CreateRecipeFormComponent {
  recipeItem: any = {
    title: '',
    description: '',
    foodType: '',
    image: '',
  };

  constructor(private recipeService: RecipeService) {}
  onSubmit() {
    console.log('values', this.recipeItem);
    this.recipeService.createRecipe(this.recipeItem).subscribe({
      next: (data) => console.log('created recipe', data),
      error: (error) => console.log('error', error),
    });
  }
}
