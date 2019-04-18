package fonobook.services;

import fonobook.models.BookingDTO;

import java.util.List;

public interface IBookingsService {

    List<BookingDTO> getAllBookings();
    BookingDTO findByPhoneModelAndEmployeeEmployeeId(String phoneModel, String employeeId);
    void createBooking(final BookingDTO booking);
    void endBooking(final BookingDTO booking);
    void extendBooking(final BookingDTO booking);

    void createBooking(String model, String employeeId);
}
