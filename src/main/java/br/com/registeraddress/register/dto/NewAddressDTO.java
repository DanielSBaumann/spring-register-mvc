package br.com.registeraddress.register.dto;

import br.com.registeraddress.register.domain.entity.Address;
import br.com.registeraddress.register.domain.entity.User;
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

    public Address toAddress(Address address) {
        address.setCep(this.cep);
        address.setState(this.state);
        address.setCity(this.city);
        address.setDistrict(this.district);
        address.setAddress(this.address);
        address.setNum(this.num);
        address.setUser(new User(this.idUser));
        return address;
    }

    public void fromAddress(Address address) {
        this.cep = address.getCep();
        this.state = address.getState();
        this.city = address.getCity();
        this.district = address.getDistrict();
        this.address = address.getAddress();
        this.num = address.getNum();
        this.idUser = address.getUser().getId();
    }
}
