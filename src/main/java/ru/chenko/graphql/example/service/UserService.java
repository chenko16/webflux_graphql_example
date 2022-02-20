package ru.chenko.graphql.example.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.dto.UserDetailDto;
import ru.chenko.graphql.example.dto.UserDto;

public interface UserService {

    Mono<UserDto> createUser(UserDto user);
    Flux<UserDto> findAllUsers();
    Mono<UserDetailDto> getUserDetailById(Long id);
    Mono<UserDto> updateUser(UserDto user);
    Mono<UserDto> deleteUser(long id);
    Flux<RoleDto> linkUserWithRole(long userId, long roleId);
    Flux<RoleDto> unlinkUserWithRole(long userId, long roleId);
}
