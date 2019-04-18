package fonobook.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Phone phone;
    @ManyToOne
    private Employee employee;
    private LocalDateTime borrowAt;
    private LocalDateTime returnedAt;
    private LocalDateTime shouldReturnAt;
}
