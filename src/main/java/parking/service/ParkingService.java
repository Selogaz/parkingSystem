package parking.service;

import parking.model.Car;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {

    private List<Car> carList = new ArrayList<>();
    private final int PARKING_ZONE_SIZE = 10;
    private final int MAXIMUM_PARKING_TIME_IN_MINUTES = 30;

    public ParkingService() {

    }

    public Car entryCar(UUID id) {
        Car newCar = new Car(id);
        if (carList.size() < PARKING_ZONE_SIZE) {
            newCar.setInsideParking(true);
            newCar.setEntryTime(LocalDateTime.now());
            System.out.println("Добавлен автомобиль с id " + id);
        } else {
            System.err.println("Въезд невозможен! Парковка заполнена!");
            return null;
        }
        carList.add(newCar);
        return newCar;
    }

    public Car exitCar(UUID id) {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                //car.setEntryTime(LocalDateTime.now().minusMinutes(40));
                long minutesParked = java.time.Duration.between(car.getEntryTime(), LocalDateTime.now()).toMinutes();
                if (minutesParked < MAXIMUM_PARKING_TIME_IN_MINUTES) {
                    carList.remove(car);
                    System.out.println("Автомобиль с id " + car.getId() + " успешно покинул парковку");
                    return car;
                } else {
                    System.err.println("Время бесплатной парковки истекло! Плоти нологе!!");
                    return car;
                }
            }
        }
        return null;
    }

    public List<Car> getEntry() {
        return carList;
    }
}
