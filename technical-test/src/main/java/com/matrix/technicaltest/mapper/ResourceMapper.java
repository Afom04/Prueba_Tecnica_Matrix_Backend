package com.matrix.technicaltest.mapper;

import com.matrix.technicaltest.dto.ResourceDTO;
import com.matrix.technicaltest.entity.Resource;

public class ResourceMapper {

    public static ResourceDTO toDTO(Resource resource) {
        if (resource == null) return null;
        return ResourceDTO.builder()
                .id(resource.getId())
                .name(resource.getName())
                .description(resource.getDescription())
                .build();
    }

    public static Resource toEntity(com.matrix.technicaltest.dto.CreateResourceDTO dto) {
        if (dto == null) return null;
        Resource resource = new Resource();
        resource.setName(dto.getName());
        resource.setDescription(dto.getDescription());
        return resource;
    }
}
