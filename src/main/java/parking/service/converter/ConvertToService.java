package parking.service.converter;

import parking.model.Entry;
import parking.model.Payment;
import parking.service.model.EntryModel;
import parking.service.model.PaymentModel;

public class ConvertToService {
    public static EntryModel entryToService(Entry entry) {
        if (entry.getPayment() == null) {
            return new EntryModel(entry.getId(), entry.getEntryTime(),
                    entry.getExitTime(),null);
        }
        PaymentModel payService = new PaymentModel(entry.getPayment().getPayTime(), entry.getPayment().getAmount());
        return new EntryModel(entry.getId(), entry.getEntryTime(),
                entry.getExitTime(),payService);
    }

    public static PaymentModel entryToService(Payment payment) {
        return new PaymentModel(payment.getPayTime(),payment.getAmount());
    }
}
