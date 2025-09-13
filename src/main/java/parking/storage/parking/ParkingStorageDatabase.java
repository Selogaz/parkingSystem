package parking.storage.parking;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import parking.model.Car;
import parking.model.Payment;
import parking.repository.ParkingRepository;
import parking.service.converter.ConvertToService;
import parking.service.model.CarService;
import parking.service.model.PaymentModelService;

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
    public UUID putEntity(CarService car) {
        Car hiberCar = new Car();
        hiberCar.setEntryTime(car.getEntryTime());
        hiberCar.setExitTime(car.getExitTime());
        parkingRepository.save(hiberCar);
        return hiberCar.getId();
    }

    @Override
    public void removeEntity(CarService car) {
        Car hiberCar = new Car();
        hiberCar.setId(car.getId());
        hiberCar.setEntryTime(car.getEntryTime());
        hiberCar.setExitTime(car.getExitTime());
        if (car.getPayment() == null) {
            parkingRepository.delete(hiberCar);
            return;
        }
        Payment payment = new Payment(hiberCar,car.getPayment().getPayTime(),car.getPayment().getAmount());
        hiberCar.setPayment(payment);
        parkingRepository.delete(hiberCar);
    }

    @Override
    public CarService getEntity(UUID id) {
        Car hiberCar = parkingRepository.getReferenceById(id);
        return ConvertToService.entryToService(hiberCar);
    }

    @Override
    public Collection<CarService> getAllEntities() {
        Collection<Car> hiberCarList = parkingRepository.findAll();
        Collection<CarService> carServiceList = new ArrayList<>();
        for (Car hiberCar : hiberCarList) {
            CarService carService = ConvertToService.entryToService(hiberCar);
            carServiceList.add(carService);
        }
        return carServiceList;
    }

    @Override
    public long size() {
        return parkingRepository.count();
    }
}
