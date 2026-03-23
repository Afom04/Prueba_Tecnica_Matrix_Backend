package com.matrix.technicaltest.controller;

import com.matrix.technicaltest.dto.CreateResourceDTO;
import com.matrix.technicaltest.dto.ResourceDTO;
import com.matrix.technicaltest.dto.UpdateResourceDTO;
import com.matrix.technicaltest.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResourceDTO create(@RequestBody @Valid CreateResourceDTO dto) {
        return resourceService.create(dto);
    }

    @GetMapping
    public Page<ResourceDTO> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return resourceService.findAll(name, page, size);
    }

    @GetMapping("/{id}")
    public ResourceDTO findById(@PathVariable Long id) {
        return resourceService.findById(id);
    }

    @PatchMapping("/{id}")
    public ResourceDTO update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateResourceDTO dto
    ) {
        return resourceService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resourceService.delete(id);
    }
}
