import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from './booking';

@Injectable({
  providedIn: 'root'
})
export class BookingsService {

  private bookingUrl = 'http://localhost:8080/bookings';

  constructor(
    private http: HttpClient
  ) { }

  getBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.bookingUrl, {
      headers: this.getHeaders(),
      responseType: 'json'
    });
  }

  getBooking(model: string, employeeId: string): Observable<Booking> {
    return this.http.get<Booking>(this.bookingUrl
      + '/' + model + '/' + employeeId, {
      headers: this.getHeaders(),
      responseType: 'json'
    });
  }

  createBooking(phoneModel: string, employee: string): Observable<Booking> {
    return this.http.post<Booking>(this.bookingUrl, {
      model: phoneModel,
      employeeId: employee
    }, {
      headers: this.getHeaders(),
      responseType: 'json'
    });
  }


  getHeaders(): HttpHeaders {
    return new HttpHeaders()
              .set('authorization', btoa('admin:admin'))
              .set('Content-Type', 'application/json');
  }
}
