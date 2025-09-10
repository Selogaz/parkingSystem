package parking.storage;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import parking.model.Car;
import parking.model.Payment;
import parking.repository.ParkingRepository;
import parking.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class PaymentStorageDatabase implements PaymentStorage{
    private PaymentRepository paymentRepository;
    private ParkingRepository parkingRepository;

    public PaymentStorageDatabase(PaymentRepository paymentRepository, ParkingRepository parkingRepository) {
        this.paymentRepository = paymentRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void makePay(Payment payment, Car car) {
        car.setPayment(payment);
        paymentRepository.save(payment);
        parkingRepository.save(car);

    }

    @Override
    public Payment getPayment(Car car) {
        return paymentRepository.getReferenceById(car.getPayment().getId());
    }


}
