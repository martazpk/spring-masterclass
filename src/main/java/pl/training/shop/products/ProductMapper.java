package pl.training.shop.products;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.FastMoneyMapper;
import pl.training.shop.common.web.PagedResultTransferObject;

import java.util.List;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface ProductMapper {

    Product toProduct(ProductTransferObject productTransferObject);

    ProductTransferObject toProductTransferObject(Product product);

    @IterableMapping(elementTargetType = ProductTransferObject.class)
    List<ProductTransferObject> toUserTransferObjects(List<Product> users);

    PagedResultTransferObject<ProductTransferObject> toProductTransferObjectsPage(PagedResult<Product> productsPage);

    @ValueMapping(source = "BOOK", target = "EBOOK")
    @ValueMapping(source = "AUDIO", target = "AUDIO")
    @ValueMapping(source = "VIDEO", target = "VIDEO")
    ProductTypeTransferObject toProductTypeTransferObject(ProductType productType);

    @InheritInverseConfiguration
    ProductType toProductType(ProductTypeTransferObject productTypeTransferObject);

}
