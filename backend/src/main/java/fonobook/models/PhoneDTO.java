package fonobook.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private String brand;
    private String model;
    private boolean available;
    @JsonBackReference
    private List<BookingDTO> bookings;
    private String technology;
    private String bands2g;
    private String bands3g;
    private String bands4g;
}
