package br.com.registeraddress.register.dto;

import br.com.registeraddress.register.domain.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String mail;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private StatusUser status;

}
