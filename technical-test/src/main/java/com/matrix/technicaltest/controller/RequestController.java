package com.matrix.technicaltest.controller;


import com.matrix.technicaltest.dto.CreateRequestDTO;
import com.matrix.technicaltest.dto.RequestDTO;
import com.matrix.technicaltest.entity.RequestStatus;
import com.matrix.technicaltest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    // =========================
    // CREATE
    // =========================
    @PostMapping
    public RequestDTO create(@RequestBody @Valid CreateRequestDTO dto) {
        return requestService.create(dto);
    }

    // =========================
    // GET ALL (PAGINADO + FILTROS)
    // =========================
    @GetMapping
    public Page<RequestDTO> findAll(
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return requestService.findAll(status, name, email, page, size);
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public RequestDTO findById(@PathVariable Long id) {
        return requestService.findById(id);
    }

    // =========================
    // UPDATE (PARTIAL)
    // =========================
    @PatchMapping("/{id}")
    public RequestDTO update(
            @PathVariable Long id,
            @RequestBody @Valid com.matrix.technicaltest.dto.UpdateRequestDTO dto
    ) {
        return requestService.update(id, dto);
    }

    // =========================
    // DELETE (SOFT DELETE)
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        requestService.delete(id);
    }
}