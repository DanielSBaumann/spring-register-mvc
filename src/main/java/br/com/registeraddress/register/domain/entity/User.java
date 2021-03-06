package br.com.registeraddress.register.domain.entity;

import br.com.registeraddress.register.domain.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Login obrigatorio")
    private String login;

    @Column
    @NotEmpty(message = "Senha obrigatorio")
    private String password;

    @Enumerated(EnumType.STRING)
    private StatusUser status;

    @Column(columnDefinition = "boolean default false")
    private boolean admin;

}
