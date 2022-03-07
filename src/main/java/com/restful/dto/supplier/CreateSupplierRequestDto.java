package com.restful.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierRequestDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email(message = "Email must be formatted")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Gender must not be blank")
    private String gender;

    @NotBlank(message = "Mobile phone must not be blank")
    private String mobilePhone;

    @NotBlank(message = "Street must not be blank")
    private String street;

    @NotBlank(message = "Kelurahan ID must not be blank")
    private String kelurahanId;
}
