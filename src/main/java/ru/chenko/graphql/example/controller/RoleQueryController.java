package ru.chenko.graphql.example.controller;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.service.RoleService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleQueryController implements GraphQLQueryResolver {

    private final RoleService roleService;

    Mono<RoleDto> getRoleById(Long id) {
        return roleService.getRoleById(id);
    }

    Mono<List<RoleDto>> getAllRoles() {
        return roleService.getAllRoles()
                .collectList();
    }
}
