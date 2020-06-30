package pl.training.shop.payments;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator generator;
    private final PaymentRepository repository;

    public FakePaymentService(@Qualifier("incrementalPaymentIdGenerator") PaymentIdGenerator generator, PaymentRepository repository) {
        this.generator = generator;
        this.repository = repository;
    }

    @LogPayments
    @Override
    public Payment process(PaymentRequest request) {
        var payment =  Payment.builder()
                .id(generator.getNext())
                .money(request.getMoney())
                .timeStamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
        repository.save(payment);
        return payment;
    }
}
