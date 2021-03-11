package br.com.registeraddress.register.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAddressDTO {

    private Integer idUser;

    @NotNull
    @NotBlank
    private String cep;

    @NotNull
    @NotBlank
    private String state;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String district;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String num;
}
