package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository repository;

    public FakePaymentService(PaymentIdGenerator paymentIdGenerator, PaymentRepository repository) {
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
}
