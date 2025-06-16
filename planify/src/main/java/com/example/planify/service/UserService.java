package com.example.planify.service;

import com.example.planify.dto.LoginDTO;
import com.example.planify.dto.UserDTO;
import com.example.planify.dto.UserUpdateDTO;
import com.example.planify.exceptions.APIExceptionResponse;
import com.example.planify.model.User;
import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserDTO addUser(UserDTO userDTO) throws JAXBException;

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    UserDTO getUserByEmailPass(LoginDTO loginDTO) throws APIExceptionResponse;

    User getUserByEmail(String email);

    List<User> deleteUser(Long id);

    User updateUser(User user);

    User updateUserPassword(UserUpdateDTO user);

    void PingUser(String email);

    void exportUsers(UserDTO userDTO) throws JAXBException;
}
