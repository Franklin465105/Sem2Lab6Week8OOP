package ie.atu.Week8OOP.Repository;

import ie.atu.Week8OOP.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}