package br.com.registeraddress.register.controller;

import br.com.registeraddress.register.domain.entity.User;
import br.com.registeraddress.register.domain.enums.StatusUser;
import br.com.registeraddress.register.domain.repository.Users;
import br.com.registeraddress.register.dto.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private Users repository;

    @GetMapping("/user")
    public ModelAndView users() {
        List<User> listUsers = repository.findAll();
        ModelAndView mv = new ModelAndView("/user/users");
        mv.addObject("users", listUsers);
        return mv;
    }

    @GetMapping("/users/new")
    public ModelAndView newUser(NewUserDTO newUserDTO) {
        ModelAndView mv = new ModelAndView("/user/newuser");
        mv.addObject("listStatusUser", StatusUser.values());
        return mv;
    }

    @PostMapping("/user")
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

    @GetMapping("/user/{id}")
    public ModelAndView show(@PathVariable Integer id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("user/showuser");
            User user = optional.get();
            mv.addObject("user", user);
            return mv;
        } else {
            return new ModelAndView("redirect:/register/user");
        }
    }

    @GetMapping("/user/{id}/edit")
    public ModelAndView edit(@PathVariable Integer id, NewUserDTO newUserDTO) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("user/edituser");
            User user = optional.get();
            newUserDTO.fromUser(user);
            mv.addObject("userId", user.getId());
            mv.addObject("listStatusUser", StatusUser.values());
            return mv;
        } else {
            return new ModelAndView("redirect:/register/user");
        }
    }

    @PostMapping("/user/{id}")
    public ModelAndView update(@PathVariable Integer id, @Valid NewUserDTO newUserDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("\n**************************ERRO******************************\n");
            ModelAndView mv = new ModelAndView("/user/edituser");
            mv.addObject("userId", id);
            mv.addObject("listStatusUser", StatusUser.values());
            return mv;
        } else {
            Optional<User> optional = repository.findById(id);
            if (optional.isPresent()) {
                User user = newUserDTO.toUser(optional.get());
                repository.save(user);
                return new ModelAndView("redirect:/register/user/" + id);
            }
            return new ModelAndView("redirect:/register/user");
        }
    }

    @GetMapping("/user/{id}/delete")
    public String delete(@PathVariable Integer id) {
        try {
            repository.deleteById(id);
            return "redirect:/register/user";
        } catch (EmptyResultDataAccessException e) {
            return "redirect:/register/user";
        }

    }
}
