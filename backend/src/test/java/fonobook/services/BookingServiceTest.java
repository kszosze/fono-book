package fonobook.services;

import fonobook.repositories.BookingRepository;
import fonobook.repositories.EmployeeRepository;
import fonobook.repositories.PhoneRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.modelmapper.ModelMapper;

import static fonobook.fixtures.BookingFixtures.BOOKING;
import static fonobook.fixtures.BookingFixtures.BOOKING_DTO;
import static fonobook.fixtures.EmployeeFixtures.EMPLOYEE;
import static fonobook.fixtures.PhonesFixtures.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PhoneRepository phoneRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private BookingService bookingService;

    @Before
    public void setUp() {
        bookingService = new BookingService(bookingRepository, employeeRepository, phoneRepository, modelMapper);
    }

    @Test
    public void getAllBookings() {
        bookingService.getAllBookings();
        verify(bookingRepository, times(1)).findAll();

    }

    @Test
    public void findByPhoneModelAndEmployeeEmployeeId() {
        bookingService.findByPhoneModelAndEmployeeEmployeeId("Samsung", "1111");
        verify(bookingRepository, times(1)).findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString());

    }

    @Test
    public void createBooking_fromDTO() {

        when(employeeRepository.findByEmployeeId(anyString())).thenReturn(EMPLOYEE);
        when(phoneRepository.findByModel(anyString())).thenReturn(PHONE);

        bookingService.createBooking(BOOKING_DTO);

        verify(bookingRepository, times(1)).save(any());
        verify(employeeRepository, times(1)).findByEmployeeId(anyString());
        verify(phoneRepository, times(1)).findByModel(anyString());

    }

    @Test
    public void createBooking_FromModelAndEmployeeId() {

        when(employeeRepository.findByEmployeeId(anyString())).thenReturn(EMPLOYEE);
        when(phoneRepository.findByModel(anyString())).thenReturn(PHONE);

        bookingService.createBooking("Samsung", "1111");

        verify(bookingRepository, times(1)).save(any());
        verify(employeeRepository, times(1)).findByEmployeeId(anyString());
        verify(phoneRepository, times(1)).findByModel(anyString());

    }

    @Test
    public void endBooking() {

        when(phoneRepository.getOne(any())).thenReturn(PHONE);
        when(bookingRepository.findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString())).thenReturn(BOOKING);

        bookingService.endBooking(BOOKING_DTO);

        verify(bookingRepository, times(1)).findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString());
        verify(bookingRepository, times(1)).save(any());
        verify(phoneRepository, times(1)).getOne(any());
    }

    @Test
    public void extendBooking() {
        when(bookingRepository.findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString())).thenReturn(BOOKING);

        bookingService.extendBooking(BOOKING_DTO);
        verify(bookingRepository, times(1)).findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString());
        verify(bookingRepository, times(1)).save(any());

    }
}