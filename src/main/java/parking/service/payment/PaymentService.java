package parking.service.payment;

import org.springframework.stereotype.Service;
import parking.service.model.CarService;
import parking.service.model.PaymentModelService;
import parking.storage.payment.PaymentStorage;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentStorage paymentStorage;
    private HashSet<UUID> payList = new HashSet<>();

    public PaymentService(PaymentStorage paymentStorage) {
        this.paymentStorage = paymentStorage;
    }

    public boolean pay(CarService car) {
        //payList.add(car.getId());
        PaymentModelService payment = new PaymentModelService(LocalDateTime.now(),100L);
        paymentStorage.makePay(payment,car);
        return isPaymentValid(car);
    }

    private boolean isPaymentValid(CarService car) {
        //if (payList.contains(id)) {
        if (paymentStorage.getPayment(car).getId().equals(car.getPayment().getId())) {
            System.out.println("Оплата для id " + car.getId() + " прошла успешно!");
            return true;
        } else {
            System.err.println("Оплата для id " + car.getId() + " не прошла! Попробуйте еще раз!");
            throw new PaymentRequiredException("Оплата для id " + car.getId() + " не прошла! Попробуйте еще раз!");
        }
    }
}
