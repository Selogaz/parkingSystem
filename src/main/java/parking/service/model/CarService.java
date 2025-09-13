package parking.service.model;

import parking.model.Payment;

import java.time.LocalDateTime;
import java.util.UUID;


public class CarService {
    private UUID id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Payment payment;

    public CarService() {
    }

    public CarService(UUID id) {
        this.id = id;
    }
}
