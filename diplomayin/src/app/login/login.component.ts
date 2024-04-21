/*import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) { }

  login() {
   
    const isAuthenticated = true;

    if (isAuthenticated) {
      this.authService.login();
      this.router.navigate(['/add-user']);
    }
  }

}*/

import { Component } from '@angular/core';
import { NgForm, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  usernameControl = new FormControl('', Validators.required);
  passwordControl = new FormControl('', Validators.required);
  errorMessage: string = '';

  isAuthenticated = true;
  constructor(private authService: AuthService, private router: Router) { }


  login(): void {
    if (this.usernameControl.valid && this.passwordControl.valid) {
      this.authService.login();
      if (this.isAuthenticated) {
        this.router.navigate(['/add-user']);
      } else {
        this.errorMessage = 'նման օգտատեր չի գտնվել'
      }

    } else {
      this.usernameControl.markAsTouched();
      this.passwordControl.markAsTouched();
    }
  }
}

