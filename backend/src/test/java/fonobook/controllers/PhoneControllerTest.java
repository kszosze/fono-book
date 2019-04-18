package fonobook.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fonobook.repositories.FoneRepository;
import fonobook.repositories.PhoneRepository;
import fonobook.services.PhoneService;
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

import static fonobook.fixtures.FoneDataFixtures.FONE_DATA;
import static fonobook.fixtures.PhonesFixtures.PHONE;
import static fonobook.fixtures.PhonesFixtures.PHONE_DTO;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PhoneControllerTest {


    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public PhoneRepository phoneRepository;

    @Mock
    public FoneRepository foneRepository;

    private PhoneService phoneService;

    private PhoneController phoneController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        phoneService = new PhoneService(phoneRepository, foneRepository, new ModelMapper());
        phoneController = new PhoneController(phoneService);

        mockMvc = MockMvcBuilders.standaloneSetup(phoneController).build();
    }

    @Test
    public void getPhones() throws Exception {
        when(phoneRepository.findAll()).thenReturn(List.of(PHONE));
        when(foneRepository.getFoneData(any())).thenReturn(List.of(FONE_DATA));

        mockMvc.perform(get("/phones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.brand", hasSize(1)));
    }

    @Test
    public void createPhone() throws Exception {

        mockMvc.perform(post("/phones", "")
                .content(objectMapper.writeValueAsString(PHONE_DTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        verify(phoneRepository, times(1)).save(any());
    }
}