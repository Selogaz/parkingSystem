package parking.service.parking;

import parking.model.Car;
import org.springframework.stereotype.Service;
import parking.service.payment.PaymentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ParkingService {

    private List<Car> carsInsideList = new ArrayList<>();
    private final int PARKING_ZONE_SIZE = 10;
    private final int MAXIMUM_PARKING_TIME_IN_MINUTES = 30;

    private final PaymentService paymentService;

    public ParkingService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Car entryCar() {
        UUID id = UUID.randomUUID();
        Car newCar = new Car(id);
        if (carsInsideList.size() < PARKING_ZONE_SIZE) {
            newCar.setInside(true);
            newCar.setEntryTime(LocalDateTime.now());
            System.out.println("Добавлен автомобиль с id " + id);
        } else {
            System.err.println("Въезд невозможен! Парковка заполнена!");
            throw new ParkingException("Въезд невозможен! Парковка заполнена!");
        }
        carsInsideList.add(newCar);
        return newCar;
    }

    public Car exitCar(UUID id) {
        for (Car car : carsInsideList) {
            if (car.getId().equals(id)) {
                long minutesParked = java.time.Duration.between(car.getEntryTime(), LocalDateTime.now()).toMinutes();
                if (minutesParked < MAXIMUM_PARKING_TIME_IN_MINUTES) {
                    car.setInside(false);
                    car.setExitTime(LocalDateTime.now());
                    carsInsideList.remove(car);
                    System.out.println("Автомобиль с id " + car.getId() + " успешно покинул парковку");
                    return car;
                } else {
                    System.out.println("Бесплатный период истек! Начинается оплата...");
                    boolean paymentSuccess = paymentService.pay(car.getId());
                    if (paymentSuccess) {
                        carsInsideList.remove(car);
                        System.out.println("Автомобиль с id " + car.getId() + " оплатил парковку и уехал");
                        return car;
                    }
                }
            }
        }
        throw new NoSuchElementException("Автомобиль с указанным ID не найден на парковке.");
    }

    public List<Car> getEntry() {
        return carsInsideList;
    }

    public Car changeEntryTime(UUID id) {
        for (Car car : carsInsideList) {
            if (car.getId().equals(id)) {
                car.setEntryTime(LocalDateTime.now().minusMinutes(40));
                return car;
            }
        }
        throw new NoSuchElementException("Не удалось изменить время въезда");
    }
}
