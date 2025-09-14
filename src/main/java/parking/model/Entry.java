package parking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "entry")
@Getter
@Setter
public class Entry {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @OneToOne(mappedBy = "entry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Payment payment;

    public Entry() {

    }

    public Entry(UUID id) {
        this.id = id;
    }
}
