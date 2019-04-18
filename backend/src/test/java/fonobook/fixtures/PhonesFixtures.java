package fonobook.fixtures;

import fonobook.models.Phone;
import fonobook.models.PhoneDTO;

public class PhonesFixtures {

    public static final PhoneDTO PHONE_DTO = PhoneDTO.builder().model("Samsung Galalxy").brand("Samsung").technology("UTMS").bands2g("GSM 850 / 900 / 1800 / 1900").bands3g("HSDPA 850 / 1900").build();

    public static final Phone PHONE = Phone.builder().model("Samsung Galalxy").brand("Samsung").id(1L).build();
}
