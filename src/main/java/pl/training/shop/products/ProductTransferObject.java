package pl.training.shop.products;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductTransferObject {
    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String price;
    private ProductTypeTransferObject type;
}
