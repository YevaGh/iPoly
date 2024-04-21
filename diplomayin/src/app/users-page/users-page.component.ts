import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-users-page',
  templateUrl: './users-page.component.html',
  styleUrl: './users-page.component.css'
})
export class UsersPageComponent implements AfterViewInit {
  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'actions'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  showAlert = false;

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private router: Router) { }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  editRow(element: any): void {
    this.router.navigate(['/edit', element.id]);

     // this.showAlert = true;
  }

  deleteRow(element: any): void {
    console.log('Delete Row:', element);
    // Implement your delete logic here
  }

  nameFilter = new FormControl('');


  nameFilterValue: string = '';
  applyFilter(value: string) {
    this.nameFilterValue = value;
    this.dataSource.filter = this.nameFilterValue.trim().toLowerCase();
  }

  applyFilter2(): void {
    // Implement your filtering logic here
    console.log(this.nameFilter.value);
  }
}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H' },
  { position: 2, name: 'Helium', weight: 4.0026, symbol: 'He' },
  { position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li' },
  { position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be' },
  { position: 5, name: 'Boron', weight: 10.811, symbol: 'B' },
  { position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C' },
  { position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N' },
  { position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O' },
  { position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F' },
  { position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne' },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na' },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na' },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na' },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na' },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na' },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na' },
]


