package pl.training.shop.orders;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.shop.common.web.FastMoneyMapper;
import pl.training.shop.common.web.IdTransferObject;
import pl.training.shop.products.Product;
import pl.training.shop.products.ProductMapper;
import pl.training.shop.products.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, FastMoneyMapper.class})
abstract class OrderMapper {

    @Autowired
    ProductService productService;

    Order toOrder(OrderTransferObject transferObject) {
        List<Product> products = transferObject.getProducts().stream()
                .map(IdTransferObject::getId)
                .map(productService::findById)
                .collect(Collectors.toList());
        return new Order(products);
    }
}
