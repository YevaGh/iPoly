import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = new BehaviorSubject<boolean>(false);
  isAuthenticated$ = this.isAuthenticated.asObservable();

  constructor() { }

  login() {
    this.isAuthenticated.next(true);
  }

  logout() {
    this.isAuthenticated.next(false);
  }

  getIsAuthenticated(): boolean {
    return this.isAuthenticated.value;
  }
}
