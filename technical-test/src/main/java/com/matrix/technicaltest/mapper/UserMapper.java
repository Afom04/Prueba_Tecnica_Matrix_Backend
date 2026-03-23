package com.matrix.technicaltest.mapper;

import com.matrix.technicaltest.dto.UserDTO;
import com.matrix.technicaltest.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}