package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private UriBuilder uriBuilder = new UriBuilder();

    @PostMapping
    ResponseEntity<Order> add(@Valid @RequestBody OrderTransferObject transferObject){
        Order order = orderMapper.toOrder(transferObject);
        Long id = orderService.add(order).getId();
        URI uri = uriBuilder.requestUriWithId(id);
        return ResponseEntity.created(uri).build();
    }
}
