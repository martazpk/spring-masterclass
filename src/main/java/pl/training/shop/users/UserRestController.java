package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;
import pl.training.shop.common.web.UriBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserRestController {

    private final UserService userService;
    private final UriBuilder uriBuilder = new UriBuilder();
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserTransferObject userTransferObject, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userMapper.toUser(userTransferObject);
        Long userId = userService.add(user).getId();
        URI locationUri = uriBuilder.requestUriWithId(userId);
        return ResponseEntity.created(locationUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserTransferObject> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        UserTransferObject transferObject = userMapper.toTransferObject(user);
        transferObject.add(linkTo(methodOn(UserRestController.class).getUser(id)).withSelfRel());
        return ResponseEntity.ok(transferObject);
    }

    @GetMapping
    PagedResultTransferObject<UserTransferObject> getUsersBySurname(
            @RequestParam String surnameFragment,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        PagedResult<User> usersPage = userService.getBySurname(surnameFragment, pageNumber, pageSize);
        return userMapper.toUserTransferObjectsPage(usersPage);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<ExceptionTransferObject> onUserNotFound(UserNotFoundException exception) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(new ExceptionTransferObject("User not found"));
//    }
}
