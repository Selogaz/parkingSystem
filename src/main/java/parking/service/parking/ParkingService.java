package parking.service.parking;

import org.springframework.transaction.annotation.Transactional;
import parking.model.Car;
import org.springframework.stereotype.Service;
import parking.service.payment.PaymentService;
import parking.storage.ParkingStorage;
import parking.storage.ParkingStorageInMemory;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    private final int PARKING_ZONE_SIZE = 10;
    private final int MAXIMUM_PARKING_TIME_IN_MINUTES = 30;

    private final PaymentService paymentService;
    private final ParkingStorage parkingStorage;

    public ParkingService(PaymentService paymentService, ParkingStorage parkingStorage) {
        this.paymentService = paymentService;
        this.parkingStorage = parkingStorage;
    }

    public Car entryCar() {
        Car newCar = new Car();
        if (parkingStorage.size() < PARKING_ZONE_SIZE) {
            newCar.setEntryTime(LocalDateTime.now());

        } else {
            System.err.println("Въезд невозможен! Парковка заполнена!");
            throw new ParkingException("Въезд невозможен! Парковка заполнена!");
        }
        parkingStorage.putEntity(newCar);
        System.out.println("Добавлен автомобиль с id " + newCar.getId());
        return newCar;
    }

    public Car exitCar(UUID id) {
        Car car = parkingStorage.getEntity(id);
        long minutesParked = java.time.Duration.between(car.getEntryTime(), LocalDateTime.now()).toMinutes();
        if (minutesParked < MAXIMUM_PARKING_TIME_IN_MINUTES) {
            car.setExitTime(LocalDateTime.now());
            parkingStorage.removeEntity(car);
            System.out.println("Автомобиль с id " + car.getId() + " успешно покинул парковку");
            return car;
        } else {
            System.out.println("Бесплатный период истек! Начинается оплата...");
            boolean paymentSuccess = paymentService.pay(car);
            if (paymentSuccess) {
                parkingStorage.removeEntity(car);
                System.out.println("Автомобиль с id " + car.getId() + " оплатил парковку и уехал");
                return car;
            }
        }
        throw new NoSuchElementException("Автомобиль с указанным ID не найден на парковке.");
    }

    public Collection<Car> getEntry() {
        return parkingStorage.getAllEntities();
    }

    public Car changeEntryTime(UUID id) {
        try {
            Car car = parkingStorage.getEntity(id);
            car.setEntryTime(LocalDateTime.now().minusMinutes(40));
            parkingStorage.putEntity(car);
            System.out.println("Время успешно изменено!");
            return car;
        } catch (Exception e) {
            throw new NoSuchElementException("Не удалось изменить время въезда");
        }
    }
}
