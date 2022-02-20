package ru.chenko.graphql.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_roles")
public class UserRolesEntity {

    @Id
    private Long id;

    private Long userId;

    private Long roleId;

}
