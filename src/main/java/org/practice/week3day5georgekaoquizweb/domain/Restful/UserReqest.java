package org.practice.week3day5georgekaoquizweb.domain.Restful;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserReqest {

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "firstname is required")
    private String firstname;

    @NotNull(message = "lastname is required")
    private String lastname;

    @NotNull(message = "Password is required")
    @Size(min=5, max=16, message = "Password must be between 3 and 72 characters")
    private String passwd;

    @NotNull(message = "Email is required")
    private String email;
}
