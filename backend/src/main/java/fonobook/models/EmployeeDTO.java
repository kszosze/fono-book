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
public class EmployeeDTO {

    private String employeeId;
    private String name;
    private String surname;
    private String role;
    private String depto;
    @JsonBackReference
    private List<BookingDTO> bookings;
}
