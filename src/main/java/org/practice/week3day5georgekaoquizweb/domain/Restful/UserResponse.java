package org.practice.week3day5georgekaoquizweb.domain.Restful;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.practice.week3day5georgekaoquizweb.domain.User;

@Getter
@Setter
@Builder
public class UserResponse {
    ResponseStatus status;
    User user;
}
