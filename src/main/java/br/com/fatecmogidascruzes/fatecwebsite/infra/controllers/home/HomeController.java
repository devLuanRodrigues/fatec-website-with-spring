package br.com.fatecmogidascruzes.fatecwebsite.infra.controllers.home;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.agendamento.Agendamento;
import br.com.fatecmogidascruzes.fatecwebsite.domain.model.agendamento.AgendamentoMapper;
import br.com.fatecmogidascruzes.fatecwebsite.service.AgendamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ModelAndView home(ModelAndView model) {
        logger.info("Accessing the home page");

        List<Agendamento> agendamentos = agendamentoService.findAll();

        model.addObject("agendamentos", agendamentos.stream().sorted(Comparator.comparing(Agendamento::getData))
                .map(AgendamentoMapper::toDTO)
                .toList());

        model.setViewName("index");

        return model;
    }
}
