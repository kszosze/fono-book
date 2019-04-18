package fonobook.controllers;

import fonobook.models.PhoneDTO;
import fonobook.services.IPhoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
@AllArgsConstructor
public class PhoneController {

    private IPhoneService phoneService;

    @GetMapping
    public List<PhoneDTO> getPhones() {
     return phoneService.getAllPhones();
    }

    @GetMapping("/{model}")
    public PhoneDTO getPhone(@PathVariable String model) {
        return phoneService.getPhoneByModel(model);
    }

    @PostMapping
    public void createPhone(@RequestBody PhoneDTO phoneDTO) {
        phoneService.addPhone(phoneDTO);
    }
}
