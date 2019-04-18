import { Component, OnInit } from '@angular/core';
import { Booking } from './booking';
import { BookingsService } from './bookings.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {

  bookings: Booking[];
  
  constructor(
    private bookingsService: BookingsService
  ) { }

  ngOnInit() {
    this.bookingsService.getBookings()
      .subscribe((response: any) =>
        this.bookings = response
      );
  }

  book(bookForm: any) {
    this.bookingsService
      .createBooking()
  }
}
