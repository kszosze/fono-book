package fonobook.controllers;

import fonobook.models.BookingDTO;
import fonobook.services.IBookingsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private IBookingsService bookingsService;

    @GetMapping
    public List<BookingDTO> getBookings() {
        return bookingsService.getAllBookings();
    }

    @GetMapping("/{model}/{employeeId}")
    public BookingDTO getBooking(@PathVariable ("model") String model, @PathVariable ("employeeId") String employeeId) {
        return bookingsService.findByPhoneModelAndEmployeeEmployeeId(model, employeeId);
    }

    @PostMapping
    public void createBooking(@RequestParam ("model") String model,@RequestParam ("employeeId") String employeeId) {
        bookingsService.createBooking(model, employeeId);
    }

    @PutMapping
    public void updateBooking(@RequestBody BookingDTO bookingDTO) {
        bookingsService.extendBooking(bookingDTO);
    }
}
