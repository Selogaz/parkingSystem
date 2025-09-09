package parking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import parking.controller.model.CarRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Car {
    private UUID id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Car() {

    }

    public Car(UUID id) {
        this.id = id;
    }
}
