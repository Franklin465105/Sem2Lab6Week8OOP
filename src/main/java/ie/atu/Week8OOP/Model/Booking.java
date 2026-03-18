package ie.atu.Week8OOP.Model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Booking {

    private Long bookingId;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @Email(message = "Student email must be valid")
    @NotBlank(message = "Student email is required")
    private String studentEmail;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @Min(value = 8, message = "Start hour must be between 8 and 18")
    @Max(value = 18, message = "Start hour must be between 8 and 18")
    private int startHour;

    @Min(value = 1, message = "Duration must be at least 1 hour")
    @Max(value = 4, message = "Duration cannot exceed 4 hours")
    private int durationHours;
}