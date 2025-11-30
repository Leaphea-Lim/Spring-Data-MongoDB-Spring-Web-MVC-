package co.istad.itp_mongodb.dto;
import jakarta.validation.constraints.NotNull;

public record CreatedUserRequest(

    @NotNull(message = "name is required")
    String name,
    @NotNull(message = "username is required")
    String username,
    @NotNull(message = "email is required")
    String email,
    @NotNull(message = "password is required")
    String password
) {
}
