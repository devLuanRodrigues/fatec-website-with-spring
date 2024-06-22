package br.com.fatecmogidascruzes.fatecwebsite.infra.controllers.admin;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento.Evento;
import br.com.fatecmogidascruzes.fatecwebsite.domain.model.evento.EventoMapper;
import br.com.fatecmogidascruzes.fatecwebsite.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ModelAndView listarAgendamentos() {
        ModelAndView model = new ModelAndView("/pages/admin/index");

        List<Evento> eventos = eventoService.findAll();

        model.addObject("eventos", eventos.stream().sorted(Comparator.comparing(Evento::getData))
                .map(EventoMapper::toDTO)
                .toList());

        return model;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/pages/login/index");
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(@ModelAttribute Evento evento) {
        eventoService.save(evento);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editarAgendamento(@PathVariable String id) {
        ModelAndView model = new ModelAndView("/pages/admin/index");

        List<Evento> eventos = eventoService.findAll();

        model.addObject("eventos", eventos.stream().sorted(Comparator.comparing(Evento::getData))
                .map(EventoMapper::toDTO)
                .toList());

        model.addObject("evento", eventoService.findById(id).orElse(new Evento()));
        model.addObject("editarEvento", true);
        return model;
    }

    @PostMapping("/{id}/deletar")
    public String deletarAgendamento(@PathVariable String id) {
        eventoService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/confirmar")
    public String confirmarAgendamento(@PathVariable String id, @ModelAttribute Evento evento) {
        eventoService.edit(id, evento);

        return "redirect:/admin";
    }
}
