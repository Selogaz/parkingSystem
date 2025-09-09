package parking.service.payment;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class PaymentService {

    private HashSet<UUID> payList = new HashSet<>();

    public PaymentService() {
    }

    public boolean pay(UUID id) {
        payList.add(id);
        return isPaymentValid(id);
    }

    private boolean isPaymentValid(UUID id) {
        if (payList.contains(id)) {
            System.out.println("Оплата для id " + id + " прошла успешно!");
            return true;
        } else {
            System.err.println("Оплата для id " + id + " не прошла! Попробуйте еще раз!");
            throw new PaymentRequiredException("Оплата для id " + id + " не прошла! Попробуйте еще раз!");
        }
    }
}
