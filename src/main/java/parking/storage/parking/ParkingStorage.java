package parking.storage.parking;

import parking.model.Car;
import parking.service.model.CarService;

import java.util.Collection;
import java.util.UUID;

public interface ParkingStorage {
    UUID putEntity(CarService car);
    void removeEntity(CarService car);
    CarService getEntity(UUID id);
    Collection<CarService> getAllEntities();
    long size();
}
