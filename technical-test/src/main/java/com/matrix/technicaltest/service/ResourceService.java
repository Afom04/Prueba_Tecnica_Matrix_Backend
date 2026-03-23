package com.matrix.technicaltest.service;

import com.matrix.technicaltest.dto.CreateResourceDTO;
import com.matrix.technicaltest.dto.ResourceDTO;
import com.matrix.technicaltest.dto.UpdateResourceDTO;
import org.springframework.data.domain.Page;

public interface ResourceService {

    ResourceDTO create(CreateResourceDTO dto);

    Page<ResourceDTO> findAll(String name, int page, int size);

    ResourceDTO findById(Long id);

    ResourceDTO update(Long id, UpdateResourceDTO dto);

    void delete(Long id);
}
