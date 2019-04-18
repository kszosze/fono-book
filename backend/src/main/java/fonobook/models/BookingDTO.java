package fonobook.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    @JsonManagedReference
    private PhoneDTO phone;
    @JsonManagedReference
    private EmployeeDTO employee;
    private LocalDateTime borrowAt;
    private LocalDateTime returnedAt;
    private LocalDateTime shouldReturnAt;
}
