package pl.training.shop.users;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "emailAddress", target = "email")
    User toUser(UserTransferObject transferObject);

    @Mapping(source = "email", target = "emailAddress")
    UserTransferObject toTransferObject(User user);

    @IterableMapping(elementTargetType = UserTransferObject.class)
    List<UserTransferObject> toUserTransferObjects(List<User> users);

    PagedResultTransferObject<UserTransferObject> toUserTransferObjectsPage(PagedResult<User> usersPage);
}
