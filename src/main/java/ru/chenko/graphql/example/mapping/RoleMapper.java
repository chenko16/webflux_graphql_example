package ru.chenko.graphql.example.mapping;

import org.mapstruct.Mapper;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.entity.RoleEntity;

@Mapper
public interface RoleMapper {

    RoleEntity toEntity(RoleDto dto);

    RoleDto toDto(RoleEntity entity);
}
