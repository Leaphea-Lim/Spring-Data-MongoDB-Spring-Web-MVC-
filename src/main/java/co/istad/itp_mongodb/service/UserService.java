package co.istad.itp_mongodb.service;

import co.istad.itp_mongodb.dto.CreatedUserRequest;
import co.istad.itp_mongodb.dto.UpdatedUserRequest;
import co.istad.itp_mongodb.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findById(String id);
    UserResponse create(CreatedUserRequest user);
    UserResponse updateById(String id, UpdatedUserRequest user);
    void deleteById(String id);
}
