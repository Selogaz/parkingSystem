package parking.service;

import parking.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {

    private List<Car> carList = new ArrayList<>();
    private final int PARKING_ZONE_SIZE = 10;

    public ParkingService() {

    }

    public Car entryCar(Long id, String carNumber) {
        Car newCar = new Car(id,carNumber);
        if (carList.size() < PARKING_ZONE_SIZE) {
            newCar.setInsideParking(true);
        } else {
            System.err.println("Въезд невозможен! Парковка заполнена!");
            return null;
        }
        carList.add(newCar);
        return newCar;
    }

    public List<Car> getEntry() {
        return carList;
    }
}
