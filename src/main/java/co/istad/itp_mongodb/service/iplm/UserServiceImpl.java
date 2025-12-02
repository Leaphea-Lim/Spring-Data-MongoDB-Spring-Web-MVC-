package co.istad.itp_mongodb.service.iplm;

import co.istad.itp_mongodb.dto.CreatedUserRequest;
import co.istad.itp_mongodb.dto.FilterDto;
import co.istad.itp_mongodb.dto.UpdatedUserRequest;
import co.istad.itp_mongodb.dto.UserResponse;
import co.istad.itp_mongodb.filter.FilteringFactory;
import co.istad.itp_mongodb.mapper.UserMapper;
import co.istad.itp_mongodb.domain.Users;
import co.istad.itp_mongodb.repository.UserRepository;
import co.istad.itp_mongodb.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Page<UserResponse> filterUsers(FilterDto filter, int page, int size) {
        Sort sortByname = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sortByname);

        Page<Users> filteredUsers = userRepository.findAllWithFilter(Users.class,
                FilteringFactory.parseFromParams(filter.filter(), Users.class), pageable);
        return filteredUsers.map(userMapper::toUseResponse);
    }

    @Override
    public Page <UserResponse> findAll(int page, int size) {

        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(page, size, sortByName);

        Page<Users> users = userRepository.findAll(pageable);

        return users.map(userMapper::toUseResponse);
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
