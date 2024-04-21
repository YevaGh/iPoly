import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrl: './main-content.component.css'
})
export class MainContentComponent {
  constructor(private router: Router) { }

  openAddUserWindow() {
    this.router.navigate(['/add-user']);
  }

  openAllUsersWindow() {
    this.router.navigate(['/all-users']);

  }
}
