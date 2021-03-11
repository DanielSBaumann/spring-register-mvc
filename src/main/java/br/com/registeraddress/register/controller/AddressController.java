package br.com.registeraddress.register.controller;

import br.com.registeraddress.register.domain.entity.Address;
import br.com.registeraddress.register.domain.repository.AddressRepo;
import br.com.registeraddress.register.dto.NewAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class AddressController {

    @Autowired
    private AddressRepo repository;

    @GetMapping("/address/{id}")
    public ModelAndView address(@PathVariable Integer id) {
        Optional<Address> optional = repository.findByUserId(id);
        if (optional.isPresent()) {

            //esse o find para caso ja exista
            //ModelAndView mv = new ModelAndView("address/viewaddress");

        } else {
            ModelAndView mv = new ModelAndView("address/address");
            mv.addObject("idUser", id);
            return mv;
        }
        return new ModelAndView("redirect:/register/user");
    }

    @PostMapping("/address")
    public ModelAndView create(@Valid NewAddressDTO dto, BindingResult bindingResult) {
        //to be continued
        return new ModelAndView("address/address");
    }
}
