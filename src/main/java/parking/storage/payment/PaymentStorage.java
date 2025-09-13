package parking.storage.payment;

import parking.model.Payment;
import parking.service.model.CarService;
import parking.service.model.PaymentModelService;

public interface PaymentStorage {
    void makePay(PaymentModelService payment, CarService car);
    PaymentModelService getPayment(CarService car);
}
