import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.component.html',
  styleUrls: ['./edit-student.component.css']
})
export class EditStudentComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }
  user: any = {
    id: '',
    role: 'student',
    name: 'yeva',
    lastname: 'ghazaryan',
    sex: 'male',
    email: 'aa@mail',
    socqart: '25213521',
    mobile: '654654',
    birthDate: '',
    password: 'hgfujgct'
  };
  showAlert = true;

  ngOnInit(): void {
    this.showAlert = true;
  }

  onSubmit(): void {
    this.showAlert = false;
  }

  generatePass(): void {
    // Implement the password generation logic here
  }
}
