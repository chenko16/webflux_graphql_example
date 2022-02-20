package ru.chenko.graphql.example.controller;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.UserDetailDto;
import ru.chenko.graphql.example.dto.UserDto;
import ru.chenko.graphql.example.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQueryController implements GraphQLQueryResolver {

    private final UserService userService;

    Mono<List<UserDto>> getAllUsers() {
        return userService.findAllUsers()
                .collectList();
    }

    Mono<UserDetailDto> getUserDetailById(Long id) {
        return userService.getUserDetailById(id);
    }
}
