package parking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Car {
    private UUID id;
    private boolean isInsideParking;
    private LocalDateTime entryTime;

    public Car() {

    }

    public Car(UUID id, boolean isInsideParking) {
        this.id = id;
        this.isInsideParking = isInsideParking;
    }

    public Car(UUID id) {
        this.id = id;
    }
}
