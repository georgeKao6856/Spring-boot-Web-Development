package org.practice.week3day5georgekaoquizweb.domain.Restful;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.practice.week3day5georgekaoquizweb.domain.User;

import java.util.List;

@Getter
@Setter
@Builder
public class AllUsersResponse {
    private ResponseStatus status;
    private List<User> users;
}
