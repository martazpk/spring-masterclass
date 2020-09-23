package pl.training.shop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.training.shop.common.PagedResult;
import pl.training.shop.common.web.PagedResultTransferObject;

@Controller
@RequiredArgsConstructor
public class UserWebController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("show-users.html")
    ModelAndView showUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize
    ){
        PagedResult<User> usersPage = userService.getAll(pageNumber, pageSize);
        PagedResultTransferObject<UserTransferObject> usersPageTO = userMapper.toUserTransferObjectsPage(usersPage);
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", usersPageTO);
        return modelAndView;
    }
}
