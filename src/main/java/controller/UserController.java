package controller;

import model.Foo;
import model.Login;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IUserManagement;

@Controller
public class UserController {

    @Autowired
    public IUserManagement userManagement;

    @GetMapping("/home")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("loginForm");
        modelAndView.addObject("login", new Login());
        return modelAndView;
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ModelAndView checkLogin(@ModelAttribute("login") Login login) {
        ModelAndView modelAndView;
        String conclude = userManagement.checkLogin(login.getAccount(), login.getPassword());
        String direct = "";
        if (conclude.equals("Login Ok")) {
            User user = userManagement.findByUserName(login.getAccount());
            modelAndView = new ModelAndView("userInfo");
            modelAndView.addObject("user", user);
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }
}
