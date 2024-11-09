package com.lemwroclaw.LEM_recruitment_website.user;

import com.lemwroclaw.LEM_recruitment_website.user.dto.UserCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserCreationDTO dto){
        return userService.createUser(dto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
