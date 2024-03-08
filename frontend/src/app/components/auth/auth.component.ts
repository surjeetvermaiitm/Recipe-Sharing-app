import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
})
export class AuthComponent {
  isRegister = false;
  constructor(public authService: AuthService) {}
  registerationForm = new FormGroup({
    fullName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
  });

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  handleRegister() {
    console.log('register', this.registerationForm.value);
    this.authService.signup(this.registerationForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log('signup success', response);
      },
    });
  }
  handleLogin() {
    console.log('login', this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log('login success', response);
      },
    });
  }
  togglePanel() {
    this.isRegister = !this.isRegister;
  }
}
