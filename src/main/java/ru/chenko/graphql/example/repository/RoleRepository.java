package ru.chenko.graphql.example.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.chenko.graphql.example.entity.RoleEntity;

public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, Long> {

    @Query("select * from roles r " +
            "join user_roles ur on r.id = ur.role_id " +
            "where ur.user_id = :userId")
    Flux<RoleEntity> findAllRolesByUserId(Long userId);

}
