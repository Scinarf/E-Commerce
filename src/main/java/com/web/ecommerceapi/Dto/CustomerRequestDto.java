package com.web.ecommerceapi.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String LastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
