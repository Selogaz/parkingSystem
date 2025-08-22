package parking.service;

import parking.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ParkingService {

    private List<Car> carList = new ArrayList<>();

    public ParkingService() {

    }

    public Car createEntry(Long id, String carNumber) {
        Car newCar = new Car(id,carNumber);
        carList.add(newCar);
        return newCar;
    }

    public List<Car> getEntry() {
        return carList;
    }
}
