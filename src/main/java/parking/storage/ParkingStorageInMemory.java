package parking.storage;

import org.springframework.stereotype.Component;
import parking.model.Car;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ParkingStorageInMemory implements ParkingStorage{
    private final Map<UUID, Car> carsInsideMap = new ConcurrentHashMap<>();

    public void putEntity(Car car) {
        carsInsideMap.put(car.getId(),car);
    }

    public void removeEntity(Car car) {
        carsInsideMap.remove(car.getId(),car);
    }

    public Car getEntity(UUID id) {
       return carsInsideMap.get(id);
    }

    public Collection<Car> getAllEntities() {
        return carsInsideMap.values();
    }

    public int size() {
       return carsInsideMap.size();
    }
}
