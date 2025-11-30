package co.istad.itp_mongodb.controller;

import co.istad.itp_mongodb.dto.CreatedUserRequest;
import co.istad.itp_mongodb.dto.UpdatedUserRequest;
import co.istad.itp_mongodb.dto.UserResponse;
import co.istad.itp_mongodb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //todo GET all users
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAll();

    }

    //todo GET user by ID
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    //todo CREATE new user
    @PostMapping
    public UserResponse create(@RequestBody CreatedUserRequest user){
        return userService.create(user);
    }

    //todo UPDATE user by id
    @PatchMapping("/{id}")
    public UserResponse updateById(@PathVariable String id, @RequestBody UpdatedUserRequest user) {
    return userService.updateById(id, user);
    }

    //todo DELETE user by id
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
         userService.deleteById(id);
    }
}
