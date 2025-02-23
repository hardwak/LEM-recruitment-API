package com.lemwroclaw.LEM_recruitment_website.user;

import com.lemwroclaw.LEM_recruitment_website.user.dto.UserAdminUpdateDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserPersonalDataUpdateDTO;
import com.lemwroclaw.LEM_recruitment_website.user.dto.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toUser(UserCreationDTO dto) {
        return User.builder()
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.valueOf(dto.role()))
                .build();
    }

    public User toUser(UserAdminUpdateDTO dto) {
        return User.builder()
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .email(dto.email())
                .role(Role.valueOf(dto.role()))
                .build();
    }

    public User toUser(UserPersonalDataUpdateDTO dto){
        return User.builder()
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .build();
    }

    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    public List<UserResponseDTO> toUserResponseDTO(List<User> users) {
        return users.stream()
                .map(this::toUserResponseDTO)
                .toList();
    }
}
