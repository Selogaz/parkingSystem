package parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parking.model.Car;

import java.util.UUID;

public interface ParkingRepository extends JpaRepository<Car, UUID> {
}
