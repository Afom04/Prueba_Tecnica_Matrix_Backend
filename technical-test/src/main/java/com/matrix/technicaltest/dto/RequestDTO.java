package com.matrix.technicaltest.dto;


import com.matrix.technicaltest.entity.RequestStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDTO {

    private Long id;
    private String applicantName;
    private String applicantEmail;
    private RequestStatus status;

    private Long resourceId;
    private String resourceName;

    private java.time.LocalDateTime createdAt;
}
