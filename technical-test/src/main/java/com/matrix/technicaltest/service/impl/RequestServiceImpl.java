package com.matrix.technicaltest.service.impl;

import com.matrix.technicaltest.dto.CreateRequestDTO;
import com.matrix.technicaltest.dto.RequestDTO;
import com.matrix.technicaltest.entity.Request;
import com.matrix.technicaltest.entity.RequestStatus;
import com.matrix.technicaltest.entity.Resource;
import com.matrix.technicaltest.mapper.RequestMapper;
import com.matrix.technicaltest.repository.RequestRepository;
import com.matrix.technicaltest.repository.ResourceRepository;
import com.matrix.technicaltest.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final ResourceRepository resourceRepository;

    // =========================
    // CREATE
    // =========================
    @Override
    public RequestDTO create(CreateRequestDTO dto) {

        Resource resource = resourceRepository.findById(dto.getResourceId())
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Resource not found"));

        Request request = RequestMapper.toEntity(dto, resource);
        request.setStatus(RequestStatus.PENDING);

        return RequestMapper.toDTO(requestRepository.save(request));
    }

    // =========================
    // FIND ALL (PAGINACIÓN + FILTROS)
    // =========================
    @Override
    public Page<RequestDTO> findAll(
            RequestStatus status,
            String name,
            String email,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        Page<Request> requests = requestRepository.search(status, name, email, pageable);

        return requests.map(RequestMapper::toDTO);
    }

    // =========================
    // FIND BY ID
    // =========================
    @Override
    public RequestDTO findById(Long id) {

        Request request = requestRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Request not found"));

        return RequestMapper.toDTO(request);
    }

    // =========================
    // UPDATE (PARTIAL)
    // =========================
    @Override
    public RequestDTO update(Long id, com.matrix.technicaltest.dto.UpdateRequestDTO dto) {

        Request request = requestRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Request not found"));

        if (dto.getApplicantName() != null && !dto.getApplicantName().trim().isEmpty()) {
            request.setApplicantName(dto.getApplicantName());
        }

        if (dto.getApplicantEmail() != null && !dto.getApplicantEmail().trim().isEmpty()) {
            request.setApplicantEmail(dto.getApplicantEmail());
        }

        if (dto.getResourceId() != null) {
            Resource resource = resourceRepository.findById(dto.getResourceId())
                    .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Resource not found"));
            request.setResource(resource);
        }

        if (dto.getStatus() != null) {
            validateStatusTransition(request.getStatus(), dto.getStatus());
            request.setStatus(dto.getStatus());
        }

        return RequestMapper.toDTO(requestRepository.save(request));
    }

    private void validateStatusTransition(RequestStatus current, RequestStatus next) {
        if (current == next) return;

        if ((current == RequestStatus.APPROVED || current == RequestStatus.REJECTED) && next == RequestStatus.PENDING) {
            throw new com.matrix.technicaltest.exception.InvalidStatusTransitionException("Cannot revert an APPROVED or REJECTED request back to PENDING");
        }
    }

    // =========================
    // DELETE (SOFT DELETE)
    // =========================
    @Override
    public void delete(Long id) {

        Request request = requestRepository.findById(id)
                .filter(r -> r.getDeletedAt() == null)
                .orElseThrow(() -> new com.matrix.technicaltest.exception.ResourceNotFoundException("Request not found"));

        request.setDeletedAt(LocalDateTime.now());

        requestRepository.save(request);
    }
}