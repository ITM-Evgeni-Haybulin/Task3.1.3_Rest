package net.zhenya.spring.rest.task3_1_3.сontroller;

import net.zhenya.spring.rest.task3_1_3.DTO.UserDto;
import net.zhenya.spring.rest.task3_1_3.exception_headling.NoSuchUserException;
import net.zhenya.spring.rest.task3_1_3.model.User;
import net.zhenya.spring.rest.task3_1_3.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Hello
@RestController
@RequestMapping("/admin/api")
public class AdminController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserDto getUserId(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("Пользователя c Id = " + id + " нет в базе данных");
        }
        return modelMapper.map(user, UserDto.class);
    }

    @PostMapping("/users")
    public UserDto addNewUser(@RequestBody User user) {
        userService.createUser(user);
        return  modelMapper.map(user, UserDto.class);
    }

    @PutMapping("/users")
    public UserDto updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return  modelMapper.map(user, UserDto.class);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("Пользователя c Id = " + id + " нет в базе данных");
        }
        userService.deleteUser(id);
        return "User id = " + id + " was deleted";
    }
}