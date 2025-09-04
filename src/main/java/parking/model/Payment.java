package parking.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class Payment {
    private UUID payId;
    private UUID carId;
    private LocalDateTime payTime;
    private Long amount;
}
