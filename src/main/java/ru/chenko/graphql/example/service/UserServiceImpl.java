package ru.chenko.graphql.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.dto.UserDetailDto;
import ru.chenko.graphql.example.dto.UserDto;
import ru.chenko.graphql.example.entity.UserEntity;
import ru.chenko.graphql.example.exceptions.EntityNotFoundException;
import ru.chenko.graphql.example.mapping.UserMapper;
import ru.chenko.graphql.example.mapping.UserRoleMapper;
import ru.chenko.graphql.example.repository.UserRepository;
import ru.chenko.graphql.example.repository.UserRolesRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleService roleService;

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    public Mono<UserDto> createUser(UserDto user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userRepository.save(userEntity)
                .map(userMapper::toDto);
    }

    @Override
    public Flux<UserDto> findAllUsers() {
        return userRepository.findAll()
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public Mono<UserDetailDto> getUserDetailById(Long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("User not found")))
                .flatMap(user -> roleService.getAllUserRoles(user.getId())
                        .collectList()
                        .map(roles -> userMapper.toDetailDto(user, roles))
                );
    }

    @Override
    public Mono<UserDto> updateUser(UserDto user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userRepository.save(userEntity)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("User not found")))
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public Mono<UserDto> deleteUser(long id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("User not found")))
                .flatMap(user -> userRepository.deleteById(user.getId())
                        .thenReturn(userMapper.toDto(user))
                );
    }

    @Override
    @Transactional
    public Flux<RoleDto> linkUserWithRole(long userId, long roleId) {
        Mono<UserEntity> foundUser = userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("User not found")));

        Mono<RoleDto> foundRole = roleService.getRoleById(roleId);

        return foundUser
                .then(foundRole)
                .then(userRolesRepository.findByUserIdAndRoleId(userId, roleId)).hasElement()
                .filter(hasRole -> !hasRole)
                .flatMap(hasRole -> userRolesRepository.save(userRoleMapper.toEntity(userId, roleId)))
                .thenMany(roleService.getAllUserRoles(userId));
    }

    @Override
    @Transactional
    public Flux<RoleDto> unlinkUserWithRole(long userId, long roleId) {
        Mono<UserEntity> foundUser = userRepository.findById(userId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("User not found")));

        Mono<RoleDto> foundRole = roleService.getRoleById(roleId);

        return foundUser
                .then(foundRole)
                .then(userRolesRepository.deleteByUserIdAndRoleId(userId, roleId))
                .thenMany(roleService.getAllUserRoles(userId));
    }

}
