package fonobook.services;

import fonobook.models.Booking;
import fonobook.models.BookingDTO;
import fonobook.models.Employee;
import fonobook.models.Phone;
import fonobook.repositories.BookingRepository;
import fonobook.repositories.EmployeeRepository;
import fonobook.repositories.PhoneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
public class BookingService implements IBookingsService {

    private BookingRepository bookingRepository;
    private EmployeeRepository employeeRepository;
    private PhoneRepository phoneRepository;
    private ModelMapper modelMapper;

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO findByPhoneModelAndEmployeeEmployeeId(String phoneModel, String employeeId) {
        return modelMapper.map(bookingRepository.findByPhoneModelAndEmployeeEmployeeId(phoneModel, employeeId), BookingDTO.class);
    }

    @Override
    public void createBooking(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        booking.setBorrowAt(LocalDateTime.now());
        booking.setShouldReturnAt(LocalDateTime.now().plus(7, DAYS));
        booking.setEmployee(employeeRepository.findByEmployeeId(bookingDTO.getEmployee().getEmployeeId()));
        Phone phone = phoneRepository.findByModel(bookingDTO.getPhone().getModel());
        phone.setAvailable(false);
        booking.setPhone(phone);
        bookingRepository.save(booking);
    }

    @Override
    public void endBooking(BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findByPhoneModelAndEmployeeEmployeeId(bookingDTO.getPhone().getModel(), bookingDTO.getEmployee().getEmployeeId());
        booking.setReturnedAt(LocalDateTime.now());
        bookingRepository.save(booking);
        Phone phone = phoneRepository.getOne(booking.getPhone().getId());
        phone.setAvailable(false);
        phoneRepository.save(phone);
    }

    @Override
    public void extendBooking(BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findByPhoneModelAndEmployeeEmployeeId(bookingDTO.getPhone().getModel(), bookingDTO.getEmployee().getEmployeeId());
        booking.setShouldReturnAt(bookingDTO.getShouldReturnAt());
        bookingRepository.save(booking);
    }

    @Override
    public void createBooking(String model, String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Phone phone = phoneRepository.findByModel(model);
        phone.setAvailable(false);
        phoneRepository.save(phone);
        Booking booking = Booking.builder().employee(employee).phone(phone).borrowAt(LocalDateTime.now()).shouldReturnAt(LocalDateTime.now().plus(7, DAYS)).build();
        bookingRepository.save(booking);
    }
}
