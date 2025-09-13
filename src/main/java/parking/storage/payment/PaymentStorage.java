package parking.storage.payment;

import parking.model.Car;
import parking.model.Payment;

public interface PaymentStorage {
    void makePay(Payment payment, Car car);
    Payment getPayment(Car car);
}
