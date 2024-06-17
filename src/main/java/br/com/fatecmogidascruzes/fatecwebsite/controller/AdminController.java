package br.com.fatecmogidascruzes.fatecwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ModelAndView admin(ModelAndView model) {
        model.setViewName("/pages/admin/index");
        return model;
    }

}
