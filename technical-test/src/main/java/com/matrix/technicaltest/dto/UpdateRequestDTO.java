package com.matrix.technicaltest.dto;

import com.matrix.technicaltest.entity.RequestStatus;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateRequestDTO {

    private String applicantName;

    @Email
    private String applicantEmail;

    private Long resourceId;

    private RequestStatus status;
}
