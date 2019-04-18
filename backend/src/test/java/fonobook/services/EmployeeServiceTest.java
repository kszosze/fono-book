package fonobook.services;

import fonobook.repositories.EmployeeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.modelmapper.ModelMapper;

import static fonobook.fixtures.EmployeeFixtures.EMPLOYEE_DTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private EmployeeService employeeService;

    @Before
    public void setUp() {
        employeeService = new EmployeeService(employeeRepository, modelMapper);
    }

    @Test
    public void getAllEmployees() {
        employeeService.getAllEmployees();
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void getEmployeeById() {
        employeeService.getEmployeeById(1L);
        verify(employeeRepository, times(1)).getOne(any());
    }

    @Test
    public void createEmployee() {
        employeeService.createEmployee(EMPLOYEE_DTO);
        verify(employeeRepository, times(1)).save(any());
    }
}