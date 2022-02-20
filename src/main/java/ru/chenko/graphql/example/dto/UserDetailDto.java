package ru.chenko.graphql.example.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDetailDto extends UserDto {

    private Set<RoleDto> roles;

}
