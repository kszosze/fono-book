package fonobook.repositories;


import fonobook.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByPhoneModelAndEmployeeEmployeeId(String model, String employeeId);
}
