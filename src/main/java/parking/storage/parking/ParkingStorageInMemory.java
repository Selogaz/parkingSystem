package parking.storage.parking;

import org.springframework.stereotype.Component;
import parking.service.model.EntryModel;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component

public class ParkingStorageInMemory implements ParkingStorage{
    private final Map<UUID, EntryModel> carsInsideMap = new ConcurrentHashMap<>();

    public UUID putEntity(EntryModel car) {
        car.setId(UUID.randomUUID());
        carsInsideMap.put(car.getId(),car);
        return car.getId();
    }

    public void removeEntity(EntryModel car) {
        carsInsideMap.remove(car.getId(),car);
    }

    public EntryModel getEntity(UUID id) {
       return carsInsideMap.get(id);
    }

    public Collection<EntryModel> getAllEntities() {
        return carsInsideMap.values();
    }

    public long size() {
       return carsInsideMap.size();
    }
}
