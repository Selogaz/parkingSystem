package parking.storage.parking;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import parking.model.Entry;
import parking.model.Payment;
import parking.repository.ParkingRepository;
import parking.service.converter.ConvertToService;
import parking.service.model.EntryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Component
@Primary
public class ParkingStorageDatabase implements ParkingStorage {
    private final ParkingRepository parkingRepository;

    public ParkingStorageDatabase(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public UUID putEntity(EntryModel car) {
        Entry hiberEntry = new Entry();
        hiberEntry.setEntryTime(car.getEntryTime());
        hiberEntry.setExitTime(car.getExitTime());
        parkingRepository.save(hiberEntry);
        return hiberEntry.getId();
    }

    @Override
    public void removeEntity(EntryModel car) {
        Entry hiberEntry = new Entry();
        hiberEntry.setId(car.getId());
        hiberEntry.setEntryTime(car.getEntryTime());
        hiberEntry.setExitTime(car.getExitTime());
        if (car.getPayment() == null) {
            parkingRepository.delete(hiberEntry);
            return;
        }
        Payment payment = new Payment(hiberEntry,car.getPayment().getPayTime(),car.getPayment().getAmount());
        hiberEntry.setPayment(payment);
        parkingRepository.delete(hiberEntry);
    }

    @Override
    public EntryModel getEntity(UUID id) {
        Entry hiberEntry = parkingRepository.getReferenceById(id);
        return ConvertToService.entryToService(hiberEntry);
    }

    @Override
    public Collection<EntryModel> getAllEntities() {
        Collection<Entry> hiberEntryList = parkingRepository.findAll();
        Collection<EntryModel> entryModelList = new ArrayList<>();
        for (Entry hiberEntry : hiberEntryList) {
            EntryModel entryModel = ConvertToService.entryToService(hiberEntry);
            entryModelList.add(entryModel);
        }
        return entryModelList;
    }

    @Override
    public long size() {
        return parkingRepository.count();
    }
}
