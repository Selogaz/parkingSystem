package parking.storage.payment;

import org.springframework.stereotype.Component;
import parking.model.Entry;
import parking.model.Payment;
import parking.repository.ParkingRepository;
import parking.repository.PaymentRepository;
import parking.service.converter.ConvertToService;
import parking.service.model.EntryModel;
import parking.service.model.PaymentModel;

@Component
public class PaymentStorageDatabase implements PaymentStorage{
    private final PaymentRepository paymentRepository;
    private final ParkingRepository parkingRepository;

    public PaymentStorageDatabase(PaymentRepository paymentRepository, ParkingRepository parkingRepository) {
        this.paymentRepository = paymentRepository;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void makePay(PaymentModel payment, EntryModel car) {
        car.setPayment(payment);

        Entry hiberEntry = new Entry();
        hiberEntry.setId(car.getId());
        hiberEntry.setEntryTime(car.getEntryTime());
        hiberEntry.setExitTime(car.getExitTime());

        Payment hiberPay = new Payment(hiberEntry,payment.getPayTime(),payment.getAmount());
        hiberEntry.setPayment(hiberPay);
        paymentRepository.save(hiberPay);
        parkingRepository.save(hiberEntry);

    }

    @Override
    public PaymentModel getPayment(EntryModel car) {
        Payment payment = paymentRepository.getReferenceById(car.getPayment().getId());
        return ConvertToService.entryToService(payment);
    }


}
