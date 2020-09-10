package pl.training.shop.orders;

import lombok.Data;
import pl.training.shop.common.web.IdTransferObject;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderTransferObject {
    @NotEmpty
    private List<IdTransferObject> products;
}
