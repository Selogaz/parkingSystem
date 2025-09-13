package parking.service.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class EntryModel {
    private UUID id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private PaymentModel payment;

    public EntryModel() {
    }

    public EntryModel(UUID id) {
        this.id = id;
    }

    public EntryModel(UUID id, LocalDateTime entryTime, LocalDateTime exitTime, PaymentModel payment) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.payment = payment;
    }
}
