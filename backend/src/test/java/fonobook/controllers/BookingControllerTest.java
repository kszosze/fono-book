package fonobook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fonobook.repositories.BookingRepository;
import fonobook.repositories.EmployeeRepository;
import fonobook.repositories.PhoneRepository;
import fonobook.services.BookingService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static fonobook.fixtures.BookingFixtures.BOOKING;
import static fonobook.fixtures.BookingFixtures.BOOKING_DTO;
import static fonobook.fixtures.EmployeeFixtures.EMPLOYEE;
import static fonobook.fixtures.PhonesFixtures.PHONE;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookingControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PhoneRepository phoneRepository;

    private BookingService bookingService;

    private BookingController bookingController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        bookingService = new BookingService(bookingRepository, employeeRepository, phoneRepository, new ModelMapper());
        bookingController = new BookingController(bookingService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    @Test
    public void getBookings() throws Exception {

        when(bookingRepository.findAll()).thenReturn(List.of(BOOKING));

        mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(bookingRepository, times(1)).findAll();

    }


    @Test
    public void getBooking() throws Exception {

        when(bookingRepository.findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString())).thenReturn(BOOKING);

        mockMvc.perform(get("/bookings/SamsungS9/1111"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(bookingRepository, times(1)).findAll();

    }

    @Test
    public void createBooking() throws Exception {
        when(employeeRepository.findByEmployeeId(anyString())).thenReturn(EMPLOYEE);
        when(phoneRepository.findByModel(anyString())).thenReturn(PHONE);

        mockMvc.perform(post("/bookings", "")
                .content(objectMapper.writeValueAsString(BOOKING_DTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());


        verify(bookingRepository, times(1)).save(any());
        verify(employeeRepository, times(1)).findByEmployeeId(anyString());
        verify(phoneRepository, times(1)).findByModel(anyString());

    }

    @Test
    public void updateBooking() throws Exception {

        when(bookingRepository.findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString())).thenReturn(BOOKING);
        mockMvc.perform(put("/bookings", "")
                .content(objectMapper.writeValueAsString(BOOKING_DTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(bookingRepository, times(1)).findByPhoneModelAndEmployeeEmployeeId(anyString(), anyString());
        verify(bookingRepository, times(1)).save(any());

    }
}