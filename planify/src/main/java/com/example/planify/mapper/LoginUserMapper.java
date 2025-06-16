package com.example.planify.mapper;

import com.example.planify.dto.LoginDTO;
import com.example.planify.model.User;

import java.util.List;
import java.util.stream.Collectors;
public class LoginUserMapper {

    public static User toEntity(LoginDTO loginDTO) {

        return User.builder()
                .email(loginDTO.getEmail())
                .password(loginDTO.getPassword())
                .build();
    }

    public static LoginDTO toDTO(User user) {
        return LoginDTO.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
    }

    public static List<LoginDTO> toDtoList(List<User> users) {
        return users.stream()
                .map(LoginUserMapper::toDTO)
                .collect(Collectors.toList());
    }




}
