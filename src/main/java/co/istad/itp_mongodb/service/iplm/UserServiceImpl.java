package co.istad.itp_mongodb.service.iplm;

import co.istad.itp_mongodb.dto.CreatedUserRequest;
import co.istad.itp_mongodb.dto.UpdatedUserRequest;
import co.istad.itp_mongodb.dto.UserResponse;
import co.istad.itp_mongodb.mapper.UserMapper;
import co.istad.itp_mongodb.model.Users;
import co.istad.itp_mongodb.repository.UserRepository;
import co.istad.itp_mongodb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> findAll() {
        List<Users> users = userRepository.findAll();

        return users.stream().map(userMapper::toUseResponse).toList();
    }

    @Override
    public UserResponse findById(String id) {
        Users user = userRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found with ID: " + id));
        return userMapper.toUseResponse(user);
    }

    @Override
    public UserResponse create(CreatedUserRequest userRequest) {

        //validation
        if (userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email already exists");
        }

        if (userRepository.existsByUsername(userRequest.username())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Username already exists");
        }

        Users user = new Users();
        user.setName(userRequest.name());
        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());

        Users savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }


    @Override
    public UserResponse updateById(String id, UpdatedUserRequest userRequest) {

        //validation
        if (userRepository.existsByUsername(userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Username already taken by another user");
        }

        Users user = new Users();
        user.setName(userRequest.name());
        user.setUsername(userRequest.username());
        Users savedUser = userRepository.save(user);
        return new  UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    @Override
    public void deleteById(String id) {

        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User not found with ID: " + id);
        }

        userRepository.deleteById(id);
    }
}
