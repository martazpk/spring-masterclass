package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.shop.common.validator.Validate;
import pl.training.shop.payments.Payment;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository repository;

    public Order add(@Validate(exception = InvalidOrderException.class) Order order) {
        order.setTimestamp(Instant.now());
        order.setPayment(Payment.builder()
                .id(UUID.randomUUID().toString())
                .money(order.getTotalPrice())
                .timeStamp(Instant.now())
                .build())
        ;
        return repository.save(order);
    }

    public Order get(Long id) {
        return repository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    public void update(Order order) {
        repository.save(order);
    }
}
