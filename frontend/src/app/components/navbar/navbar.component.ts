import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { routes } from '../../app.routes';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatButtonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  user: any;

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.authService.authSubject.subscribe((auth) => {
      console.log('auth state value', auth);
      this.user = auth.user;
      console.log('user', this.user);
    });
  }

  handleLogout() {
    this.authService.logout();
    // this.router.navigate('/');
  }
}
