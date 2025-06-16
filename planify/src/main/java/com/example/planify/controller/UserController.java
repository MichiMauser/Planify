package com.example.planify.controller;

import com.example.planify.dto.LoginDTO;
import com.example.planify.dto.UserDTO;
import com.example.planify.dto.UserUpdateDTO;
import com.example.planify.exceptions.APIExceptionResponse;
import com.example.planify.mapper.UserMapper;
import com.example.planify.model.Notebook;
import com.example.planify.model.User;
import com.example.planify.service.NotebookService;
import com.example.planify.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.xml.bind.JAXBException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final NotebookService notebookService;

    public UserController(UserService userService, NotebookService notebookService) {
        this.userService = userService;
        this.notebookService = notebookService;
    }

    @Operation(
            summary = "Get all users or user details by ID",
            description = "Fetches all users if no ID is provided. If an ID is passed, returns the user and their notebooks."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched user(s)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping({"", "/{id}"})
    public ResponseEntity<?> showUsers(@PathVariable(required = false) Long id) throws NoSuchElementException {
        try {
            if (id != null) {
                Optional<User> user = userService.getUserById(id);
                List<Notebook> notebooks = notebookService.findNotebookByUserId(user.get().getId());
                Map<String, Object> response = new HashMap<>();
                response.put("user", user);
                response.put("notebooks", notebooks);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                List<User> users = userService.getAllUsers();
                return ResponseEntity.status(HttpStatus.OK).body(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(
            summary = "User login",
            description = "Authenticate a user with email and password"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws APIExceptionResponse{

        UserDTO userDTO = userService.getUserByEmailPass(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toEntity(userDTO));
    }

    @Operation(
            summary = "Add a new user",
            description = "Create and save a new user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully")
    })
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) throws APIExceptionResponse, JAXBException {
        userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toEntity(userDTO));
    }

    @Operation(
            summary = "Delete a user by ID",
            description = "Deletes the user with the specified ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully")
    })
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws APIExceptionResponse {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User " + id + " deleted successfully");
    }

    @Operation(
            summary = "Update a user",
            description = "Update user information"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully")
    })
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO updateData) throws APIExceptionResponse {

        userService.updateUserPassword(updateData);
        return ResponseEntity.status(HttpStatus.OK).body(updateData.toString());
    }

    @PostMapping("/message")
    public ResponseEntity<?> pingUser(@RequestBody String message) throws APIExceptionResponse {
        userService.PingUser(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
