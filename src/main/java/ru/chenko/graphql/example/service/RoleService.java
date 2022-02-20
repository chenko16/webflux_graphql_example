package ru.chenko.graphql.example.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;

public interface RoleService {

    Mono<RoleDto> createRole(RoleDto role);
    Mono<RoleDto> updateRole(RoleDto role);
    Flux<RoleDto> getAllRoles();
    Mono<RoleDto> getRoleById(long id);
    Flux<RoleDto> getAllUserRoles(long userId);
    Mono<RoleDto> deleteRole(long id);

}
