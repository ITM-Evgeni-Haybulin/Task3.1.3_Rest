package net.zhenya.spring.rest.task3_1_3.сontroller;

import net.zhenya.spring.rest.task3_1_3.DTO.UserDto;
import net.zhenya.spring.rest.task3_1_3.model.User;
import net.zhenya.spring.rest.task3_1_3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/Me")
    public UserDto getCurrentUser(Principal principal) {
        User user = userService.findByFirstname(principal.getName());
        return  modelMapper.map(user, UserDto.class);
    }
}
