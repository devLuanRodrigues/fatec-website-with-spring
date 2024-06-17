package br.com.fatecmogidascruzes.fatecwebsite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public ModelAndView home(ModelAndView model) {
        logger.info("Accessing the home page");

        // add index.html to model and return the view without using 'return "index"'
        model.setViewName("index");

        return model;
    }
}
