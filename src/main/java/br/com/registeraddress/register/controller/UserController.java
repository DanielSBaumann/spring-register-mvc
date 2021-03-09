package br.com.registeraddress.register.controller;

import br.com.registeraddress.register.domain.entity.User;
import br.com.registeraddress.register.domain.enums.StatusUser;
import br.com.registeraddress.register.domain.repository.Users;
import br.com.registeraddress.register.dto.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private Users repository;

    @GetMapping("/register/user")
    public ModelAndView users() {
        List<User> listUsers = repository.findAll();
        ModelAndView mv = new ModelAndView("/user/users");
        mv.addObject("users", listUsers);
        return mv;
    }

    @GetMapping("/register/users/new")
    public ModelAndView newUser(NewUserDTO newUserDTO) {
        ModelAndView mv = new ModelAndView("/user/newuser");
        mv.addObject("listStatusUser", StatusUser.values());
        return mv;
    }

    @PostMapping("/register/user")
    public ModelAndView create(@Valid NewUserDTO newUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("\n**************************ERRO******************************\n");
            ModelAndView mv = new ModelAndView("/user/newuser");
            mv.addObject("listStatusUser", StatusUser.values());
            return mv;
        } else {
            User user = User
                    .builder()
                    .name(newUserDTO.getName())
                    .password(newUserDTO.getPassword())
                    .mail(newUserDTO.getMail())
                    .status(newUserDTO.getStatus())
                    .build();
            repository.save(user);
            return new ModelAndView("redirect:/register/user");
        }
    }
}
