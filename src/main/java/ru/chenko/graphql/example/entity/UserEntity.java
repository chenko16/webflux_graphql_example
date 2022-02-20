package ru.chenko.graphql.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class UserEntity {

    @Id
    private Long id;

    private String login;

    private String name;

    private String password;

}
