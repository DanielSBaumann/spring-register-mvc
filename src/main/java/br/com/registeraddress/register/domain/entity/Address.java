package br.com.registeraddress.register.domain.entity;

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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Cep obrigatorio")
    private String cep;

    @Column
    @NotEmpty(message = "Estado obrigatorio")
    private String state;

    @Column
    @NotEmpty(message = "Cidade obrigatorio")
    private String city;

    @Column
    @NotEmpty(message = "Bairro obrigatorio")
    private String district;

    @Column
    @NotEmpty(message = "Endereco obrigatorio")
    private String address;

    @Column
    @NotEmpty(message = "Numero obrigatorio")
    private String num;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
