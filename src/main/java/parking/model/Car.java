package parking.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private Long id;
    private String carNumber;
    //private boolean isInsideParking;

    public Car() {

    }

    public Car(Long id, String carNumber) {
        this.id = id;
        this.carNumber = carNumber;
    }
}
