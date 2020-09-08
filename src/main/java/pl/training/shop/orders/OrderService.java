package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.training.shop.common.validator.Validate;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository repository;

    public Order add(@Validate(exception = InvalidOrderException.class)Order order) {
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
