package com.matrix.technicaltest.service.impl;

import com.matrix.technicaltest.dto.CreateResourceDTO;
import com.matrix.technicaltest.dto.ResourceDTO;
import com.matrix.technicaltest.dto.UpdateResourceDTO;
import com.matrix.technicaltest.entity.Resource;
import com.matrix.technicaltest.mapper.ResourceMapper;
import com.matrix.technicaltest.repository.ResourceRepository;
import com.matrix.technicaltest.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResourceDTO create(CreateResourceDTO dto) {
        Resource resource = ResourceMapper.toEntity(dto);
        return ResourceMapper.toDTO(resourceRepository.save(resource));
    }

    @Override
    public Page<ResourceDTO> findAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Resource> resources = resourceRepository.search(name, pageable);
        return resources.map(ResourceMapper::toDTO);
    }

    @Override
    public ResourceDTO findById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null) // Assuming BaseEntity has getDeletedAt()
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Resource not found"));
        return ResourceMapper.toDTO(resource);
    }

    @Override
    public ResourceDTO update(Long id, UpdateResourceDTO dto) {
        Resource resource = resourceRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Resource not found"));

        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            resource.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            resource.setDescription(dto.getDescription());
        }

        return ResourceMapper.toDTO(resourceRepository.save(resource));
    }

    @Override
    public void delete(Long id) {
        Resource resource = resourceRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Resource not found"));

        // Soft delete assuming BaseEntity has setDeletedAt()
        resource.setDeletedAt(LocalDateTime.now());
        resourceRepository.save(resource);
    }
}
