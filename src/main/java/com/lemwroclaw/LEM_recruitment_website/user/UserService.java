package com.lemwroclaw.LEM_recruitment_website.user;

import com.lemwroclaw.LEM_recruitment_website.user.dto.UserCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

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

}
