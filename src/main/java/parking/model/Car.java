package parking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Car {
    private Long id;
    private String carNumber;
    private boolean isInsideParking;
    private LocalDateTime entryTime;

    public Car() {

    }

    public Car(Long id, String carNumber, boolean isInsideParking) {
        this.id = id;
        this.carNumber = carNumber;
        this.isInsideParking = isInsideParking;
    }

    public Car(Long id, String carNumber) {
        this.id = id;
        this.carNumber = carNumber;
    }
}
