package com.matrix.technicaltest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateResourceDTO {

    @NotBlank
    private String name;

    private String description;
}
