package fonobook.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String employeeId;
    private String name;
    private String surname;
    private String role;
    private String depto;
    @OneToMany
    private List<Booking> bookings;
}
