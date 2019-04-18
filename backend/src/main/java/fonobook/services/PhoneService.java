package fonobook.services;

import fonobook.models.FoneData;
import fonobook.models.FoneDataRequest;
import fonobook.models.Phone;
import fonobook.models.PhoneDTO;
import fonobook.repositories.FoneRepository;
import fonobook.repositories.PhoneRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PhoneService implements IPhoneService {

    private PhoneRepository phoneRepository;
    private FoneRepository foneRepository;
    private ModelMapper modelMapper;

    @Override
    public List<PhoneDTO> getAllPhones() {
        return phoneRepository.findAll().stream()
                .map(phone -> modelMapper.map(phone, PhoneDTO.class))
                .map(this::enrichData)
                .collect(Collectors.toList());
    }

    @Override
    public PhoneDTO getPhoneByModel(final String model) {
        Phone phone = phoneRepository.findByModel(model);
        if (null == phone) {
            return null;
        }
        return enrichData(modelMapper.map(phone, PhoneDTO.class));
    }

    @Override
    public void addPhone(PhoneDTO phone) {
        phoneRepository.save(modelMapper.map(phone, Phone.class));
    }

    private PhoneDTO enrichData(PhoneDTO phoneDTO) {
        List<FoneData> foneDataList = foneRepository.getFoneData(FoneDataRequest.builder().device(phoneDTO.getModel()).build());
        if (!foneDataList.isEmpty()) {
            foneDataList
                    .stream()
                    .filter(deviceData -> deviceData.getDeviceName().equalsIgnoreCase(phoneDTO.getModel()))
                    .findAny().ifPresent(deviceData -> {
                        phoneDTO.setBands2g(deviceData.getBands2g());
                        phoneDTO.setBands3g(deviceData.getBands3g());
                        phoneDTO.setBands4g(deviceData.getBands4g());
                        phoneDTO.setTechnology(deviceData.getTechnology());
                    });
        }
        return phoneDTO;
    }
}
