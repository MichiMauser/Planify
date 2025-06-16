//package com.example.planify.service.impl;
//
//import com.example.planify.dto.LoginDTO;
//import com.example.planify.dto.UserDTO;
//import com.example.planify.exceptions.APIExceptionResponse;
//import com.example.planify.model.User;
//import com.example.planify.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.hamcrest.Matchers.any;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private SimpMessagingTemplate messagingTemplate;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserImpl userService;
//
//    @Test
//    void testAddUser_Success() throws Exception {
//        UserDTO dto = new UserDTO();
//        dto.setEmail("test@example.com");
//        dto.setPassword("plain");
//
//        User user = new User();
//        user.setEmail(dto.getEmail());
//        user.setPassword("encoded");
//
//        when(passwordEncoder.encode("plain")).thenReturn("encoded");
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        UserDTO result = userService.addUser(dto);
//
//        assertEquals("test@example.com", result.getEmail());
//        verify(userRepository).save(any(User.class));
//    }
//
//    @Test
//    void testGetUserByEmailPass_WrongPassword_ThrowsException() {
//        LoginDTO loginDTO = new LoginDTO("test@example.com", "wrongpass");
//
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("encoded");
//
//        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
//        when(passwordEncoder.matches("wrongpass", "encoded")).thenReturn(false);
//
//        assertThrows(APIExceptionResponse.class, () -> {
//            userService.getUserByEmailPass(loginDTO);
//        });
//    }
//}
