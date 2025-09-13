package parking.storage.payment;

import org.springframework.stereotype.Component;
import parking.model.Car;
import parking.model.Payment;
import parking.repository.ParkingRepository;
import parking.repository.PaymentRepository;
import parking.service.converter.ConvertToService;
import parking.service.model.CarService;
import parking.service.model.PaymentModelService;

@Component
public class PaymentStorageDatabase implements PaymentStorage{
    private final PaymentRepository paymentRepository;
    private final ParkingRepository parkingRepository;

    public PaymentStorageDatabase(PaymentRepository paymentRepository, ParkingRepository parkingRepository) {
        this.paymentRepository = paymentRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void makePay(PaymentModelService payment, CarService car) {
        car.setPayment(payment);

        Car hiberCar = new Car();
        hiberCar.setId(car.getId());
        hiberCar.setEntryTime(car.getEntryTime());
        hiberCar.setExitTime(car.getExitTime());

        Payment hiberPay = new Payment(hiberCar,payment.getPayTime(),payment.getAmount());
        hiberCar.setPayment(hiberPay);
        paymentRepository.save(hiberPay);
        parkingRepository.save(hiberCar);

    }

    @Override
    public PaymentModelService getPayment(CarService car) {
        Payment payment = paymentRepository.getReferenceById(car.getPayment().getId());
        return ConvertToService.entryToService(payment);
    }


}
