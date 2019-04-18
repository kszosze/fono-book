import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeesService } from './employees.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees: Employee[];

  constructor(
    private employeeService: EmployeesService;
  ) { }

  ngOnInit() {
    this.employeeService.getEmployees()
      .subscribe((response: any) =>
        this.employees = response
      );
  }

}
