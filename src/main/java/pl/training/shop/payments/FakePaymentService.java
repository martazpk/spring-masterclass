package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.Instant;

@Log
@RequiredArgsConstructor
public class FakePaymentService {
    private static final String LOG_FORMAT = "A new Payment of %s has been initialized";
    private final PaymentIdGenerator generator;

    public Payment process(PaymentRequest request){
        var payment = Payment.builder()
                .id(generator.getNext())
                .money(request.getMoney())
                .timeStamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        log.info(createLogEntry(payment));
        return payment;
    }

    private String createLogEntry(Payment payment){
        return String.format(LOG_FORMAT, payment.getMoney());
    }
}
