package com.lemwroclaw.LEM_recruitment_website.user;

import com.lemwroclaw.LEM_recruitment_website.auth.AuthenticationService;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserAdminUpdateDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserPasswordUpdateDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    //admin
    public ResponseEntity<UserResponseDTO> createUser(UserCreationDTO dto){
        try {
            Role.valueOf(dto.role());
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toUser(dto);
        userRepository.save(user);

        UserResponseDTO responseDTO = userMapper.toUserResponseDTO(user);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserResponseDTO)
                .toList();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(users);
    }

    public ResponseEntity<UserResponseDTO> getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userMapper.toUserResponseDTO(user));
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public ResponseEntity<UserResponseDTO> updateUser(Long id, UserAdminUpdateDTO dto){
        User user = userRepository.getReferenceById(id);

        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setRole(Role.valueOf(dto.role()));

        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toUserResponseDTO(user));
    }

    //own account

    public ResponseEntity<?> changePassword(UserPasswordUpdateDTO dto){
        var user = userRepository.getReferenceById(authenticationService.getAuthenticatedUser().getId());

        if (!passwordEncoder.matches(dto.oldPassword(), user.getPassword())){
            return new ResponseEntity<>("Old Password is incorrect", HttpStatus.FORBIDDEN);
        }

        user.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("Password changed", HttpStatus.ACCEPTED);
    }

//    public ResponseEntity<>
}
