package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public class LoggingPaymentService implements PaymentService{
    private final FakePaymentService fakePaymentService;

    private static final String LOG_FORMAT = "A new Payment of %s has been initialized";

    @Override
    public Payment process(PaymentRequest request) {
        var payment = fakePaymentService.process(request);
        log.info(createLogEntry(payment));
        return payment;
    }

    private String createLogEntry(Payment payment){
        return String.format(LOG_FORMAT, payment.getMoney());
    }
}
