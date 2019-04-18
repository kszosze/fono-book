package fonobook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fonobook.repositories.EmployeeRepository;
import fonobook.services.EmployeeService;
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

import static fonobook.fixtures.EmployeeFixtures.EMPLOYEE;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        employeeService = new EmployeeService(employeeRepository, new ModelMapper());
        employeeController = new EmployeeController(employeeService);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getEmployees() throws Exception {
        when(employeeRepository.findAll()).thenReturn(List.of(EMPLOYEE));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void createEmployee() throws Exception {

        mockMvc.perform(post("/employees", "")
                    .content(objectMapper.writeValueAsString(EMPLOYEE))
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(employeeRepository, times(1)).save(any());
    }
}