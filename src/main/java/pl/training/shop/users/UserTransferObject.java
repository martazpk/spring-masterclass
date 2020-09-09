package pl.training.shop.users;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserTransferObject extends RepresentationModel<UserTransferObject> {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Email
    private String emailAddress;
}
