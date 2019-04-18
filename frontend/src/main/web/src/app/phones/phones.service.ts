import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Phones } from './phones';

@Injectable({
  providedIn: 'root'
})
export class PhonesService {
  private phonesUrl = 'http://localhost:8008/phones';

  constructor(
    private http: HttpClient
  ) { }

  getPhones(): Observable<Phones[]> {
    return this.http.get<Phones[]>(this.phonesUrl, {
      headers: this.getHeaders(),
      responseType: 'json'
    } );
  }

  getPhone(model: string): Observable<Phones> {
    return this.http.get<Phones>(this.phonesUrl + '/' + model, {
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
