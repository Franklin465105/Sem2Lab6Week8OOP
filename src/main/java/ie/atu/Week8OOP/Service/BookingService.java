package ie.atu.Week8OOP.Service;

import ie.atu.Week8OOP.Model.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookingService {

    private final List<Booking> bookingList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Booking addBooking(Booking booking) {

        for (Booking existing : bookingList) {

            // ONLY check conflict if SAME room AND SAME date
            if (existing.getRoomNumber().equalsIgnoreCase(booking.getRoomNumber())
                    && existing.getBookingDate().equals(booking.getBookingDate())) {

                int existingStart = existing.getStartHour();
                int existingEnd = existingStart + existing.getDurationHours();

                int newStart = booking.getStartHour();
                int newEnd = newStart + booking.getDurationHours();

                // Check if times overlap
                if (newStart < existingEnd && existingStart < newEnd) {
                    throw new IllegalArgumentException(
                            "Room " + booking.getRoomNumber() +
                                    " is already booked from " + existingStart +
                                    " to " + existingEnd
                    );
                }
            }
        }

        booking.setBookingId(idCounter.incrementAndGet());
        bookingList.add(booking);
        return booking;
    }

    public List<Booking> getAllBookings() {
        return bookingList;
    }

    public Booking getBookingById(Long id) {
        return bookingList.stream()
                .filter(b -> b.getBookingId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Booking with id " + id + " not found"));
    }
}