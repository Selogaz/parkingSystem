package parking.service;

import parking.dto.Response;
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

    private final PaymentService paymentService;

    public ParkingService(PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    public Response indexEntry(UUID id) {
        Response response = new Response();
        response.setCarId(id);
        if (entryCar(id)) {
            return response;
        } else {
            response.setErrorMsg("Hе удалось заехать на парковку");
            return response;
        }
    }

    public Response indexExit(UUID id) {
        Response response = new Response();
        response.setCarId(id);
        if (exitCar(id)) {
            return response;
        } else {
            response.setErrorMsg("Hе удалось выехать с парковки");
            return response;
        }
    }

    public Response indexChangeTime(UUID id) {
        Response response = new Response();
        response.setCarId(id);
        if (changeEntryTime(id)) {
            return response;
        } else {
            response.setErrorMsg("Не удалось изменить время въезда");
            return response;
        }
    }

    public boolean entryCar(UUID id) {
        Car newCar = new Car(id);
        if (carList.size() < PARKING_ZONE_SIZE) {
            newCar.setInsideParking(true);
            newCar.setEntryTime(LocalDateTime.now());
            System.out.println("Добавлен автомобиль с id " + id);
        } else {
            System.err.println("Въезд невозможен! Парковка заполнена!");
            return false;
        }
        carList.add(newCar);
        return true;
    }

    public boolean exitCar(UUID id) {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                long minutesParked = java.time.Duration.between(car.getEntryTime(), LocalDateTime.now()).toMinutes();
                if (minutesParked < MAXIMUM_PARKING_TIME_IN_MINUTES) {
                    carList.remove(car);
                    System.out.println("Автомобиль с id " + car.getId() + " успешно покинул парковку");
                    return true;
                } else {
                    System.err.println("Время бесплатной парковки истекло! Плоти нологе!!");
                    return paymentService.pay(car.getId());
                }
            }
        }
        return false;
    }

    public List<Car> getEntry() {
        return carList;
    }

    public boolean changeEntryTime(UUID id) {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                car.setEntryTime(LocalDateTime.now().minusMinutes(40));
                return true;
            }
        }
        return false;
    }
}
