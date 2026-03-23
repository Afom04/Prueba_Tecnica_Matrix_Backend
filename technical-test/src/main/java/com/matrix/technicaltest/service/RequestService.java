package com.matrix.technicaltest.service;

import com.matrix.technicaltest.dto.CreateRequestDTO;
import com.matrix.technicaltest.dto.RequestDTO;
import com.matrix.technicaltest.entity.RequestStatus;

import org.springframework.data.domain.Page;

public interface RequestService {

    RequestDTO create(CreateRequestDTO dto);

    Page<RequestDTO> findAll(
            RequestStatus status,
            String name,
            String email,
            int page,
            int size
    );

    RequestDTO findById(Long id);

    RequestDTO update(Long id, com.matrix.technicaltest.dto.UpdateRequestDTO dto);

    void delete(Long id);
}