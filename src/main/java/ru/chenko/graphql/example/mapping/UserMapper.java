package ru.chenko.graphql.example.mapping;

import org.mapstruct.Mapper;
import ru.chenko.graphql.example.dto.RoleDto;
import ru.chenko.graphql.example.dto.UserDetailDto;
import ru.chenko.graphql.example.dto.UserDto;
import ru.chenko.graphql.example.entity.UserEntity;

import java.util.Collection;

@Mapper
public interface UserMapper {

    UserEntity toEntity(UserDto dto);

    UserDto toDto(UserEntity entity);

    UserDetailDto toDetailDto(UserEntity user, Collection<RoleDto> roles);
}
