package br.com.registeraddress.register.dto;

import br.com.registeraddress.register.domain.entity.User;
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

    public User toUser(User user) {
        user.setName(this.name);
        user.setMail(this.mail);
        user.setPassword(this.password);
        user.setStatus(this.status);
        return user;
    }

    public void fromUser(User user) {
        this.name = user.getName();
        this.mail = user.getMail();
        this.password = user.getPassword();
        this.status = user.getStatus();
    }

}
