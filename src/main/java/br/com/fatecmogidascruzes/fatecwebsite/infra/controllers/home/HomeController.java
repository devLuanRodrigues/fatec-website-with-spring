package br.com.fatecmogidascruzes.fatecwebsite.infra.controllers.home;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento.Evento;
import br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento.EventoMapper;
import br.com.fatecmogidascruzes.fatecwebsite.service.EventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ModelAndView home(ModelAndView model) {
        logger.info("Accessing the home page");

        List<Evento> eventos = eventoService.findAll();

        model.addObject("eventos", eventos.stream().sorted(Comparator.comparing(Evento::getData))
                .map(EventoMapper::toDTO)
                .toList());

        model.setViewName("index");

        return model;
    }
}
