package ru.chenko.graphql.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.entity.RoleEntity;
import ru.chenko.graphql.example.exceptions.EntityNotFoundException;
import ru.chenko.graphql.example.mapping.RoleMapper;
import ru.chenko.graphql.example.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Mono<RoleDto> createRole(RoleDto role) {
        RoleEntity roleEntity = roleMapper.toEntity(role);
        return roleRepository.save(roleEntity)
                .map(roleMapper::toDto);
    }

    @Override
    public Mono<RoleDto> updateRole(RoleDto role) {
        RoleEntity roleEntity = roleMapper.toEntity(role);
        return roleRepository.save(roleEntity)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Role not found")))
                .map(roleMapper::toDto);
    }

    @Override
    public Flux<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .map(roleMapper::toDto);
    }

    @Override
    public Mono<RoleDto> getRoleById(long id) {
        return roleRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Role not found")))
                .map(roleMapper::toDto);
    }

    @Override
    public Flux<RoleDto> getAllUserRoles(long userId) {
        return roleRepository.findAllRolesByUserId(userId)
                .map(roleMapper::toDto);
    }

    @Override
    @Transactional
    public Mono<RoleDto> deleteRole(long id) {
        return roleRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Role not found")))
                .flatMap(role -> roleRepository.deleteById(role.getId())
                        .thenReturn(roleMapper.toDto(role))
                );
    }
}
