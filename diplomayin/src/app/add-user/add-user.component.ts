import { Component } from '@angular/core';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css',
})
export class AddUserComponent {
  user = {
    role: '',
    name: '',
    lastname: '',
    sex: '',
    email: '',
    socqart: '',
    mobile: '',
    birthDate: '',
    password: ''
  };


  showAlert = false;

  onSubmit(): void {

    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 5000);
  }

  generatePass(): void {
    this.user.password = Math.random().toString(36).slice(-10); 
  }

}
