package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.training.shop.common.profiler.ExecutionTime;

import javax.transaction.Transactional;
import java.time.Instant;

@Log
@RequiredArgsConstructor
@Service
@Transactional
public class FakePaymentService implements PaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @ExecutionTime
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
        eventPublisher.publishEvent(new PaymentStatusChangedEvent(this, payment));
        return payment;
    }

}
