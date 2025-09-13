package parking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "car_id",
            referencedColumnName = "id",
            nullable = true
    )
    private Car car;

    @Column(name = "pay_time")
    private LocalDateTime payTime;

    @Column(name = "amount")
    private Long amount;

    public Payment(Car car, LocalDateTime payTime, Long amount) {
        this.car = car;
        this.payTime = payTime;
        this.amount = amount;
    }



    public Payment() {

    }
}
