package co.istad.itp_mongodb.service;

import co.istad.itp_mongodb.dto.CreatedUserRequest;
import co.istad.itp_mongodb.dto.FilterDto;
import co.istad.itp_mongodb.dto.UpdatedUserRequest;
import co.istad.itp_mongodb.dto.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<UserResponse> filterUsers(FilterDto filter,
                                   int page,
                                   int size);
    Page<UserResponse> findAll(int page, int size);
    UserResponse findById(String id);
    UserResponse create(CreatedUserRequest user);
    UserResponse updateById(String id, UpdatedUserRequest user);
    void deleteById(String id);

}
