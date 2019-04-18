package fonobook.services;

import fonobook.models.PhoneDTO;

import java.util.List;

public interface IPhoneService {

    List<PhoneDTO> getAllPhones();
    void addPhone(final PhoneDTO phone);

    PhoneDTO getPhoneByModel(String model);
}
