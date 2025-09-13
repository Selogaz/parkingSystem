package parking.storage.parking;

import parking.service.model.EntryModel;

import java.util.Collection;
import java.util.UUID;

public interface ParkingStorage {
    UUID putEntity(EntryModel car);
    void removeEntity(EntryModel car);
    EntryModel getEntity(UUID id);
    Collection<EntryModel> getAllEntities();
    long size();
}
