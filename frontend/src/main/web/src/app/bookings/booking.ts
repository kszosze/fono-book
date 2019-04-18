import { Phones } from '../phones/phones';
import { Employee } from '../employees/employee';

export class Booking {
  phone: Phones;
  employee: Employee;
  borrowAt: string;
  returnedAt: string;
  shouldReturnAt: string;
}
