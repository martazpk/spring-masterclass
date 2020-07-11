package pl.training.shop;

import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.shop.orders.Order;
import pl.training.shop.payments.*;
import pl.training.shop.products.Product;
import pl.training.shop.products.ProductType;

import java.util.Collections;
import java.util.List;

@Log
public class Application {
    private static final String BASE_PACKAGE = "pl.training.shop";

    private static final Product VIDEO_PRODUCT = Product.builder()
            .name("Spring Masterclass")
            .description("kurs Spring Framework")
            .price(LocalMoney.of(1_000))
            .type(ProductType.VIDEO)
            .build();

    private static final Product BOOK_PRODUCT = Product.builder()
            .name("Spring in Action")
            .description("Spring guide")
            .price(LocalMoney.of(50))
            .type(ProductType.BOOK)
            .build();

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BASE_PACKAGE)) {
            var shopService = context.getBean(ShopService.class);
            shopService.addProduct(VIDEO_PRODUCT);
            shopService.addProduct(BOOK_PRODUCT);

            log.info(shopService.getProducts(0, 10).toString());

            Order order = new Order(Collections.emptyList());

            shopService.placeOrder(order);
            var payment = shopService.payForOrder(order.getId());

            log.info(payment.getId());
        }
    }
}
