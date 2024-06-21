package br.com.fatecmogidascruzes.fatecwebsite.controller;

import br.com.fatecmogidascruzes.fatecwebsite.model.Agendamento;
import br.com.fatecmogidascruzes.fatecwebsite.service.AgendamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ModelAndView home(ModelAndView model) {
        logger.info("Accessing the home page");

        Iterable<Agendamento> agendamentos = agendamentoService.findAll();

        model.addObject("agendamentos", agendamentos);

        model.setViewName("index");

        return model;
    }
}
