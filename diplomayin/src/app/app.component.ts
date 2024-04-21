import { Component, ElementRef, Renderer2} from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthService } from './service/auth.service';
import { filter } from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'diplomayin';
  isAuthenticated: boolean = false;

  constructor(private router: Router, private authService: AuthService, private renderer: Renderer2,
    private el: ElementRef) { }

  ngOnInit(): void {
    this.authService.isAuthenticated$.subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  openAddUserWindow() {
    this.renderer.setStyle(this.el.nativeElement.ownerDocument.body, 'background-image', 'url("../assets/poli.jpg")');

    this.router.navigate(['/add-user']);
    
  }

  openAllUsersWindow() {
    this.router.navigate(['/all-users']);

  }
}
