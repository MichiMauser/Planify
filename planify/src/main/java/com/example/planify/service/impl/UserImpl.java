    package com.example.planify.service.impl;

    import com.example.planify.constans.NotificationsEndpoint;
    import com.example.planify.dto.LoginDTO;
    import com.example.planify.dto.UserDTO;
    import com.example.planify.dto.UserUpdateDTO;
    import com.example.planify.exceptions.APIExceptionResponse;
    import com.example.planify.exporter.FileExporterToXML;
    import com.example.planify.mapper.UserMapper;
    import com.example.planify.model.User;
    import com.example.planify.repository.UserRepository;
    import com.example.planify.service.UserService;
    import jakarta.xml.bind.JAXBException;
    import org.springframework.http.HttpStatus;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Component;

    import java.util.Collections;
    import java.util.List;
    import java.util.NoSuchElementException;
    import java.util.Optional;

    import org.springframework.messaging.simp.SimpMessagingTemplate;

    @Component
    public class UserImpl implements UserService {

        private final UserRepository userRepository;
        private final SimpMessagingTemplate simpMessagingTemplate;
        private final PasswordEncoder encoder;
        public UserImpl(UserRepository userRepository, SimpMessagingTemplate simpMessagingTemplate, PasswordEncoder encoder) {
            this.userRepository = userRepository;
            this.simpMessagingTemplate = simpMessagingTemplate;
            this.encoder = encoder;
        }

        @Override
        public UserDTO addUser(UserDTO userDTO) throws JAXBException {
            User user = UserMapper.toEntity(userDTO);
            user.setPassword(encoder.encode(userDTO.getPassword()));
            userRepository.save(user);
            exportUsers(userDTO);
            return userDTO;
        }

        @Override
        public List<User> getAllUsers() {
            userRepository.findAll();
            return userRepository.findAll();
        }

        @Override
        public Optional<User> getUserById(Long id) {
            return userRepository.findById(id);
        }
        @Override
        public UserDTO getUserByEmailPass(LoginDTO loginDTO) throws APIExceptionResponse{

            User user = userRepository.findByEmail(loginDTO.getEmail());
            if(user == null){
                throw APIExceptionResponse.builder()
                        .errors(Collections.singletonList("Credentials are wrong!"))
                        .message("Entity not found")
                        .status(HttpStatus.NOT_FOUND)
                        .build();


            }
            if(encoder.matches(loginDTO.getPassword(), user.getPassword())){
                return UserMapper.toDto(user);
            }
            throw APIExceptionResponse.builder()
                    .errors(Collections.singletonList("Password is wrong!"))
                    .message("Password is wrong")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        @Override
        public User getUserByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        @Override
        public List<User> deleteUser(Long id) {
            userRepository.deleteById(id);
            return userRepository.findAll();

        }

        @Override
        public User updateUser(User user) {
            User oldUser;
            Optional<User> optionalUser = userRepository.findById(user.getId());
            if(optionalUser.isPresent()){
                oldUser = optionalUser.get();

                oldUser.setPassword(user.getPassword());
                oldUser.setFirstName(user.getFirstName());
                oldUser.setLastName(user.getLastName());
                oldUser.setEmail(user.getEmail());
                oldUser.setGender(user.getGender());

                return userRepository.save(oldUser);
            }else{
                throw new NoSuchElementException();
            }


        }

        @Override
        public User updateUserPassword(UserUpdateDTO updateDTO) {
            User oldUser;

            if(userRepository.findByEmail(updateDTO.getEmail()) != null){
                oldUser = userRepository.findByEmail(updateDTO.getEmail());
                oldUser.setPassword(updateDTO.getPassword());
                return userRepository.save(oldUser);
            }else{
                throw new NoSuchElementException();
            }
        }

        @Override
        public void PingUser(String msg) {


            simpMessagingTemplate.convertAndSend(NotificationsEndpoint.MESSAGE, msg);


        }

        @Override
        public void exportUsers(UserDTO userDTO) throws JAXBException {

            FileExporterToXML fileExporter  = new FileExporterToXML();
            fileExporter.exportData(userDTO);
        }


    }
