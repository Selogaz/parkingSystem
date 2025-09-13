package parking.storage.parking;

import org.springframework.stereotype.Component;
import parking.model.Car;
import parking.service.model.CarService;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component

public class ParkingStorageInMemory implements ParkingStorage{
    private final Map<UUID, CarService> carsInsideMap = new ConcurrentHashMap<>();

    public UUID putEntity(CarService car) {
        car.setId(UUID.randomUUID());
        carsInsideMap.put(car.getId(),car);
        return car.getId();
    }

    public void removeEntity(CarService car) {
        carsInsideMap.remove(car.getId(),car);
    }

    public CarService getEntity(UUID id) {
       return carsInsideMap.get(id);
    }

    public Collection<CarService> getAllEntities() {
        return carsInsideMap.values();
    }

    public long size() {
       return carsInsideMap.size();
    }
}
