package parking.service.converter;

import parking.model.Car;
import parking.model.Payment;
import parking.service.model.CarService;
import parking.service.model.PaymentModelService;

public class ConvertToService {
    public static CarService entryToService(Car car) {
        if (car.getPayment() == null) {
            return new CarService(car.getId(),car.getEntryTime(),
                    car.getExitTime(),null);
        }
        PaymentModelService payService = new PaymentModelService(car.getPayment().getPayTime(),car.getPayment().getAmount());
        return new CarService(car.getId(),car.getEntryTime(),
                car.getExitTime(),payService);
    }

    public static PaymentModelService entryToService(Payment payment) {
        return new PaymentModelService(payment.getPayTime(),payment.getAmount());
    }
}
