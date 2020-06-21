package pl.training.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.time.Instant;

@Log
@RequiredArgsConstructor
public class FakePaymentService implements PaymentService {
    private final PaymentIdGenerator generator;

    @Override
    public Payment process(PaymentRequest request){
        return Payment.builder()
                .id(generator.getNext())
                .money(request.getMoney())
                .timeStamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
    }
}
