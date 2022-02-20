package ru.chenko.graphql.example.controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.dto.UserDto;
import ru.chenko.graphql.example.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMutationController implements GraphQLMutationResolver {

    private final UserService userService;

    public Mono<UserDto> createUser(UserDto user) {
        return userService.createUser(user);
    }

    public Mono<UserDto> updateUser(UserDto user) {
        return userService.updateUser(user);
    }

    public Mono<UserDto> deleteUser(long id) {
        return userService.deleteUser(id);
    }

    public Flux<RoleDto> linkUserWithRole(long userId, long roleId) {
        return userService.linkUserWithRole(userId, roleId);
    }

    public Mono<List<RoleDto>> unlinkUserWithRole(long userId, long roleId) {
        return userService.unlinkUserWithRole(userId, roleId)
                .collectList();
    }
}
