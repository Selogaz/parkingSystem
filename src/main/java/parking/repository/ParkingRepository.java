package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parking.model.Entry;

import java.util.UUID;

public interface ParkingRepository extends JpaRepository<Entry, UUID> {
}
