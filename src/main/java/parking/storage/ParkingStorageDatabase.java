package parking.storage;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import parking.model.Car;
import parking.repository.ParkingRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@Primary
public class ParkingStorageDatabase implements ParkingStorage {
    private ParkingRepository parkingRepository;

    public ParkingStorageDatabase(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void putEntity(Car car) {
        parkingRepository.save(car);
    }

    @Override
    public void removeEntity(Car car) {
        parkingRepository.delete(car);
    }

    @Override
    public Car getEntity(UUID id) {
        return parkingRepository.getReferenceById(id);
    }

    @Override
    public Collection<Car> getAllEntities() {
        return parkingRepository.findAll();
    }

    @Override
    public long size() {
        return parkingRepository.count();
    }
}
