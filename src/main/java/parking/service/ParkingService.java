package parking.service;

import parking.dto.Response;
import parking.exceptions.ParkingException;
import parking.exceptions.PaymentRequiredException;
import parking.model.Car;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    public Response getResponse(UUID id) {
        Response response = new Response();
        response.setCarId(id);
        return response;
    }

    public Response entryCar(UUID id) {
        Car newCar = new Car(id);
        if (carList.size() < PARKING_ZONE_SIZE) {
            newCar.setInsideParking(true);
            newCar.setEntryTime(LocalDateTime.now());
            System.out.println("Добавлен автомобиль с id " + id);
        } else {
            System.err.println("Въезд невозможен! Парковка заполнена!");
            throw new ParkingException("Въезд невозможен! Парковка заполнена!");
        }
        carList.add(newCar);
        return getResponse(id);
    }

    public Response exitCar(UUID id) {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                long minutesParked = java.time.Duration.between(car.getEntryTime(), LocalDateTime.now()).toMinutes();
                if (minutesParked < MAXIMUM_PARKING_TIME_IN_MINUTES) {
                    carList.remove(car);
                    System.out.println("Автомобиль с id " + car.getId() + " успешно покинул парковку");
                    return getResponse(id);
                } else {
                    System.out.println("Бесплатный период истек! Начинается оплата...");
                    boolean paymentSuccess = paymentService.pay(car.getId());
                    if (paymentSuccess) {
                        carList.remove(car);
                        System.out.println("Автомобиль с id " + car.getId() + " оплатил парковку и уехал");
                        return getResponse(id);
                    }
                }
            }
        }
        throw new NoSuchElementException("Автомобиль с указанным ID не найден на парковке.");
    }

    public List<Car> getEntry() {
        return carList;
    }

    public Response changeEntryTime(UUID id) {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                car.setEntryTime(LocalDateTime.now().minusMinutes(40));
                return getResponse(id);
            }
        }
        throw new NoSuchElementException("Не удалось изменить время въезда");
    }
}
