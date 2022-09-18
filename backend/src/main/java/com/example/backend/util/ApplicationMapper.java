package com.example.backend.util;

import com.example.backend.model.dto.UserDto;
import com.example.backend.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationMapper() {
        // Mapper utility class
    }

    public UserDto userToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullName(user.getFullName());
        dto.setVersion(user.getVersion());
        return dto;
    }
}
