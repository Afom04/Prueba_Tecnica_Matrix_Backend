package com.matrix.technicaltest.service;

import com.matrix.technicaltest.dto.UpdateUserDTO;
import com.matrix.technicaltest.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserDTO> findAll(int page, int size);
    UserDTO findById(Long id);
    UserDTO update(Long id, UpdateUserDTO dto);
    void delete(Long id);
}
