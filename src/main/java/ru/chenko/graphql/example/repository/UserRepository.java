package ru.chenko.graphql.example.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.chenko.graphql.example.entity.UserEntity;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}
