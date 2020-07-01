package pl.training.shop.payments;

import lombok.extern.java.Log;

import java.time.Instant;

@Log
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository repository;

    public FakePaymentService(@IdGenerator("uuid") PaymentIdGenerator paymentIdGenerator, PaymentRepository repository) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.repository = repository;
    }

    @LogPayments
    @Override
    public Payment process(PaymentRequest request) {
        var payment =  Payment.builder()
                .id(paymentIdGenerator.getNext())
                .money(request.getMoney())
                .timeStamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        repository.save(payment);
        return payment;
    }

    public void init(){
        log.info("Payment service initialized. ");
    }

    public void destroy(){
        log.info("Payment service is going down.");
    }
}
