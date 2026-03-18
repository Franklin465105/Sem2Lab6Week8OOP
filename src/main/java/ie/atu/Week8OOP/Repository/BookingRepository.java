package ie.atu.Week8OOP.Repository;

import ie.atu.Week8OOP.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDate(LocalDate bookingDate);
    List<Booking> findByStudentEmail(String studentEmail);
    List<Booking> findByStudentEmailAndBookingDate(String studentEmail, LocalDate bookingDate);
}