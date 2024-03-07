import { Component } from '@angular/core';
import { RecipeCardComponent } from '../recipe-card/recipe-card.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RecipeCardComponent, MatIconModule, MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  recipes = [1, 1, 1, 1, 1, 1, 1];

  constructor(public dialog: MatDialog) {}

  handleOpenCreateRecipeForm() {
    this.dialog.open(CreateRecipeFormComponent);
  }
}
