package br.com.registeraddress.register.controller;

import br.com.registeraddress.register.domain.entity.Address;
import br.com.registeraddress.register.domain.entity.User;
import br.com.registeraddress.register.domain.repository.AddressRepo;
import br.com.registeraddress.register.domain.repository.Users;
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
    private Users userRepository;

    @Autowired
    private AddressRepo repository;

    @GetMapping("/address/{id}")
    public ModelAndView address(@PathVariable Integer id) {
        Optional<Address> optional = repository.findByUserId(id);
        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("address/showaddress");
            Address address = optional.get();
            mv.addObject("address", address);
            mv.addObject("userId", address.getUser().getId());
            return mv;
        } else {
            Optional<User> op = userRepository.findById(id);
            if (op.isPresent()) {
                ModelAndView mv = new ModelAndView("address/address");
                mv.addObject("idUser", id);
                return mv;
            } else {
                return new ModelAndView("redirect:/register/user");
            }
        }
    }

    @PostMapping("/address")
    public ModelAndView create(@Valid NewAddressDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("address/address");
            mv.addObject("idUser", dto.getIdUser());
            return mv;
        } else {
            User user = userRepository
                    .findById(dto.getIdUser())
                    .get();
            Address address = Address
                    .builder()
                    .user(user)
                    .cep(dto.getCep())
                    .state(dto.getState())
                    .city(dto.getCity())
                    .district(dto.getDistrict())
                    .address(dto.getAddress())
                    .num(dto.getNum())
                    .build();
            repository.save(address);
            ModelAndView mv = new ModelAndView("address/showaddress");
            mv.addObject("address", address);
            mv.addObject("userId", address.getUser().getId());
            return mv;
        }
    }

    @GetMapping("/address/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, NewAddressDTO newAddressDTO) {
        Optional<Address> optional = repository.findByUserId(id);
        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("address/editaddress");
            Address address = optional.get();
            newAddressDTO.fromAddress(address);
            mv.addObject("userId", id);
            return mv;
        } else {
            return new ModelAndView("redirect:/register/user");
        }
    }

    @PostMapping("/address/{id}")
    public ModelAndView update(@PathVariable Integer id, @Valid NewAddressDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("address/editaddress");
            mv.addObject("userId", id);
            return mv;
        } else {
            Optional<Address> optional = repository.findByUserId(id);
            if (optional.isPresent()) {
                Address address = dto.toAddress(optional.get());
                User user = userRepository.findById(id).get();
                address.setUser(user);
                repository.save(address);
                return new ModelAndView("redirect:/register/address/" + id);
            }
            return new ModelAndView("redirect:/register/user");
        }
    }
}
















