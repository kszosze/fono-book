import { Booking } from '../bookings/booking';

export class Employee {
  employeeId: string;
  name: string;
  surname: string;
  role: string;
  depto: string;
  bookings: Booking[];

 fullName(): string {
   return name + ' ' + this.surname;
 }
}
