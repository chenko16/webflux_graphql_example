package ru.chenko.graphql.example.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.chenko.graphql.example.entity.UserRolesEntity;

public interface UserRolesRepository extends ReactiveCrudRepository<UserRolesEntity, Long> {

    Mono<UserRolesEntity> findByUserIdAndRoleId(Long userId, Long roleId);
    Mono<Void> deleteByUserIdAndRoleId(Long userId, Long roleId);

}
