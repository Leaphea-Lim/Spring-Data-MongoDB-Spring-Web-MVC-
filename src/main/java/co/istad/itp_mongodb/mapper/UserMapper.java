package co.istad.itp_mongodb.mapper;

import co.istad.itp_mongodb.dto.UpdatedUserRequest;
import co.istad.itp_mongodb.dto.UserResponse;
import co.istad.itp_mongodb.domain.Users;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUseResponse(Users user);

//    @Mapping(target = "id", ignore = true)
//    Users toUser(UserResponse userResponse);
//
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void toUserPartially (UpdatedUserRequest updatedUserRequest, @MappingTarget Users user);
}
