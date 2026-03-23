package com.matrix.technicaltest.mapper;

import com.matrix.technicaltest.dto.CreateRequestDTO;
import com.matrix.technicaltest.dto.RequestDTO;
import com.matrix.technicaltest.entity.Request;
import com.matrix.technicaltest.entity.Resource;

public class RequestMapper {

    public static RequestDTO toDTO(Request request) {
        return RequestDTO.builder()
                .id(request.getId())
                .applicantName(request.getApplicantName())
                .applicantEmail(request.getApplicantEmail())
                .status(request.getStatus())
                .resourceId(request.getResource().getId())
                .resourceName(request.getResource().getName())
                .createdAt(request.getCreatedAt())
                .build();
    }
    public static Request toEntity(CreateRequestDTO dto, Resource resource) {
        Request request = new Request();
        request.setApplicantName(dto.getApplicantName());
        request.setApplicantEmail(dto.getApplicantEmail());
        request.setResource(resource);
        return request;
    }
}
