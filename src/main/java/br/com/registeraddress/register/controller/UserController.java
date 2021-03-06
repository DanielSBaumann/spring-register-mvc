package br.com.registeraddress.register.controller;

import br.com.registeraddress.register.domain.entity.User;
import br.com.registeraddress.register.domain.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private Users repository;

    @GetMapping("/register/users")
    public ModelAndView users() {
        List<User> listUsers = repository.findAll();
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("users", listUsers);
        return mv;
    }
}
