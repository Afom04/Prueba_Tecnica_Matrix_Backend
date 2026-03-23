package com.matrix.technicaltest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRequestDTO {

    @NotBlank
    private String applicantName;

    @NotBlank
    @Email
    private String applicantEmail;

    @NotNull
    private Long resourceId;
}
