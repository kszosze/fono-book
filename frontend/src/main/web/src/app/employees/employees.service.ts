import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Employee } from './employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  private employesUrl = 'http://localhost:8080/employees';
  constructor(
    private http: HttpClient
  ) { }

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.employesUrl, {
      headers: this.getHeaders(),
      responseType: 'json'
    } );
  }

  getHeaders(): HttpHeaders {
    return new HttpHeaders()
              .set('authorization', btoa('admin:admin'))
              .set('Content-Type', 'application/json');
  }
}
