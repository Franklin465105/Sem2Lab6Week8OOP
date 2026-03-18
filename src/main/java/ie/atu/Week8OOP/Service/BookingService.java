package ie.atu.Week8OOP.Service;

import ie.atu.Week8OOP.Model.Booking;
import ie.atu.Week8OOP.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking addBooking(Booking booking) {

        List<Booking> bookingList = bookingRepository.findAll();

        for (Booking existing : bookingList) {
            if (existing.getRoomNumber().equalsIgnoreCase(booking.getRoomNumber())
                    && existing.getBookingDate().equals(booking.getBookingDate())) {

                int existingStart = existing.getStartHour();
                int existingEnd = existingStart + existing.getDurationHours();

                int newStart = booking.getStartHour();
                int newEnd = newStart + booking.getDurationHours();

                if (newStart < existingEnd && existingStart < newEnd) {
                    throw new IllegalArgumentException(
                            "Room " + booking.getRoomNumber() +
                                    " is already booked from " + existingStart +
                                    " to " + existingEnd
                    );
                }
            }
        }

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Booking with id " + id + " not found"));
    }

    // Get by date
    public List<Booking> getAllBookingsByDate(LocalDate bookingDate) {
        return bookingRepository.findByBookingDate(bookingDate);
    }

    // Get by student email
    public List<Booking> getBookingsByStudentEmail(String studentEmail) {
        return bookingRepository.findByStudentEmail(studentEmail);
    }

    // Get by student email and date
    public List<Booking> getBookingsByStudentEmailAndDate(String studentEmail, LocalDate bookingDate) {
        return bookingRepository.findByStudentEmailAndBookingDate(studentEmail, bookingDate);
    }
}