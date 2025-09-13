package parking.service.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PaymentModelService {
    private UUID id;
    private LocalDateTime payTime;
    private Long amount;

    public PaymentModelService(LocalDateTime payTime, Long amount) {
        this.payTime = payTime;
        this.amount = amount;
    }

    public PaymentModelService() {

    }
}
