package parking.storage;

import parking.model.Car;

import java.util.Collection;
import java.util.UUID;

public interface ParkingStorage {
    void putEntity(Car car);
    void removeEntity(Car car);
    Car getEntity(UUID id);
    Collection<Car> getAllEntities();
    long size();
}
