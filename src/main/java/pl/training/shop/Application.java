package pl.training.shop;

import lombok.extern.java.Log;
import pl.training.shop.payments.*;

@Log
public class Application {
    public static void main(String[] args) {
        var paymentGenerator = new IncrementalPaymentIdGenerator();
        var paymentService = new FakePaymentService(paymentGenerator);
        var paymentRequest = PaymentRequest.builder()
                .money(LocalMoney.of(1_000))
                .build();
        var payment = paymentService.process(paymentRequest);
        log.info(payment.toString());
    }
}
