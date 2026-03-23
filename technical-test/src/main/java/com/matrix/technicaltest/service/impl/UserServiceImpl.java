package com.matrix.technicaltest.service.impl;

import com.matrix.technicaltest.dto.UpdateUserDTO;
import com.matrix.technicaltest.dto.UserDTO;
import com.matrix.technicaltest.entity.User;
import com.matrix.technicaltest.exception.ResourceNotFoundException;
import com.matrix.technicaltest.mapper.UserMapper;
import com.matrix.technicaltest.repository.UserRepository;
import com.matrix.technicaltest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return userRepository.findAll(pageable).map(UserMapper::toDTO);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .filter(u -> u.getDeletedAt() == null)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO update(Long id, UpdateUserDTO dto) {
        User user = userRepository.findById(id)
                .filter(u -> u.getDeletedAt() == null)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            user.setEmail(dto.getEmail());
        }
        
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .filter(u -> u.getDeletedAt() == null)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
                
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
