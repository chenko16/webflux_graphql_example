package ru.chenko.graphql.example.controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.service.RoleService;

@Component
@RequiredArgsConstructor
public class RoleMutationController implements GraphQLMutationResolver {

    private final RoleService roleService;

    Mono<RoleDto> createRole(RoleDto role) {
        return roleService.createRole(role);
    }

    Mono<RoleDto> updateRole(RoleDto role) {
        return roleService.updateRole(role);
    }

    Mono<RoleDto> deleteRole(long id) {
        return roleService.deleteRole(id);
    }

}
