package fonobook.fixtures;

import fonobook.models.Booking;
import fonobook.models.BookingDTO;

import java.time.LocalDateTime;

import static fonobook.fixtures.EmployeeFixtures.EMPLOYEE;
import static fonobook.fixtures.EmployeeFixtures.EMPLOYEE_DTO;
import static fonobook.fixtures.PhonesFixtures.PHONE;
import static fonobook.fixtures.PhonesFixtures.PHONE_DTO;

public class BookingFixtures {

    public static final Booking BOOKING = Booking.builder().employee(EMPLOYEE).phone(PHONE).borrowAt(LocalDateTime.now()).shouldReturnAt(LocalDateTime.MAX).id(1L).build();

    public static final BookingDTO BOOKING_DTO = BookingDTO.builder().employee(EMPLOYEE_DTO).phone(PHONE_DTO).build();

}
