package fonobook.services;

import fonobook.repositories.FoneRepository;
import fonobook.repositories.PhoneRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.modelmapper.ModelMapper;

import java.util.List;

import static fonobook.fixtures.FoneDataFixtures.FONE_DATA;
import static fonobook.fixtures.PhonesFixtures.PHONE;
import static fonobook.fixtures.PhonesFixtures.PHONE_DTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PhoneServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private FoneRepository foneRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private PhoneService phoneService;

    @Before
    public void setUp() {
        phoneService = new PhoneService(phoneRepository, foneRepository, modelMapper);
    }

    @Test
    public void getAllPhones() {
        when(foneRepository.getFoneData(any())).thenReturn(List.of(FONE_DATA));
        when(phoneRepository.findAll()).thenReturn(List.of(PHONE));
        phoneService.getAllPhones();

        verify(foneRepository, times(1)).getFoneData(any());
        verify(phoneRepository, times(1)).findAll();
    }

    @Test
    public void getPhoneByModel() {
        phoneService.getPhoneByModel("Samsung S9");
        verify(phoneRepository, times(1)).getOne(any());
    }

    @Test
    public void addPhone() {
        phoneService.addPhone(PHONE_DTO);

        verify(phoneRepository, times(1)).save(any());
    }
}