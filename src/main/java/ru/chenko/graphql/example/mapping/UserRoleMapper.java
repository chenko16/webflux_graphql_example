package ru.chenko.graphql.example.mapping;

import org.mapstruct.Mapper;
import ru.chenko.graphql.example.entity.UserRolesEntity;

@Mapper
public interface UserRoleMapper {

    UserRolesEntity toEntity(Long userId, Long roleId);
}
