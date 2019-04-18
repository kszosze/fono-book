package fonobook;

import fonobook.repositories.BookingRepository;
import fonobook.repositories.EmployeeRepository;
import fonobook.repositories.FoneRepository;
import fonobook.repositories.PhoneRepository;
import fonobook.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FonoBookConfiguration {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private FoneRepository foneRepository;

    @Bean
    public IBookingsService bookingService() {
        return new BookingService(bookingRepository, employeeRepository, phoneRepository, modelMapper());
    }

    @Bean
    public IEmployeeService employeeService() {
        return new EmployeeService(employeeRepository, modelMapper());
    }

    @Bean
    public IPhoneService phoneService() {
        return new PhoneService(phoneRepository, foneRepository, modelMapper());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
