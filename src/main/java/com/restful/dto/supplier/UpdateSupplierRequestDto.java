package com.restful.dto.supplier;

import com.restful.entity.enumerator.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupplierRequestDto {


    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email(message = "Email must valid format")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotNull(message = "Gender can not null")
    private Gender gender;

    @NotBlank(message = "Mobile phone must not be blank")
    private String mobilePhone;

    @NotBlank(message = "Street must not be blank")
    private String street;

    // kita kirim postal code dulu, tapi field street dan postal code akan disimpan di beda table
    @NotBlank(message = "Postal code must not be blank")
    private String postalCode;

    @NotBlank(message = "Kelurahan ID must not be blank")
    private String kelurahanId;
}
