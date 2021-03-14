package br.com.registeraddress.register.domain.entity;

import br.com.registeraddress.register.domain.enums.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "user")
public class User {

    public User(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Nome obrigatorio")
    private String name;

    @Column
    @NotEmpty(message = "Email obrigatorio")
    private String mail;

    @Column
    @NotEmpty(message = "Senha obrigatorio")
    private String password;

    @Enumerated(EnumType.STRING)
    private StatusUser status;

    @Column(columnDefinition = "boolean default false")
    private boolean admin;

    @OneToOne(mappedBy = "user")
    private Address address;

}
