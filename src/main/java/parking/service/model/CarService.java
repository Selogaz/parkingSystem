package parking.service.model;

import lombok.Getter;
import lombok.Setter;
import parking.model.Payment;
import parking.service.payment.PaymentService;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class CarService {
    private UUID id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PaymentModelService payment;

    public CarService() {
    }

    public CarService(UUID id) {
        this.id = id;
    }

    public CarService(UUID id, LocalDateTime entryTime, LocalDateTime exitTime, PaymentModelService payment) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.payment = payment;
    }
}
