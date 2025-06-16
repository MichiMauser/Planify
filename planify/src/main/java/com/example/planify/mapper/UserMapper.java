package com.example.planify.mapper;

import com.example.planify.dto.UserDTO;
import com.example.planify.model.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword()) // Will be encoded in service
                .age(dto.getAge())
                .gender(dto.getGender())
                .role(dto.getRole())
                .build();
    }

    public static UserDTO toDto(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password("") // Don't expose password
                .age(user.getAge())
                .gender(user.getGender())
                .role(user.getRole())
                .build();
    }
}
