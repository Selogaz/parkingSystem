package parking.storage.payment;

import parking.service.model.EntryModel;
import parking.service.model.PaymentModel;

public interface PaymentStorage {
    void makePay(PaymentModel payment, EntryModel car);
    PaymentModel getPayment(EntryModel car);
}
