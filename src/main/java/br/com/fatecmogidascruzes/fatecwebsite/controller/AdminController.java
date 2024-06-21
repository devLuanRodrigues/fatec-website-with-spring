package br.com.fatecmogidascruzes.fatecwebsite.controller;

import br.com.fatecmogidascruzes.fatecwebsite.model.Agendamento;
import br.com.fatecmogidascruzes.fatecwebsite.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ModelAndView listarAgendamentos() {
        ModelAndView model = new ModelAndView("/pages/admin/index");
        model.addObject("agendamentos", agendamentoService.findAll());
        return model;
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento) {
        agendamentoService.save(agendamento);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editarAgendamento(@PathVariable String id) {
        ModelAndView model = new ModelAndView("/pages/admin/index");
        model.addObject("agendamento", agendamentoService.findById(id).orElse(new Agendamento()));
        model.addObject("agendamentos", agendamentoService.findAll());
        return model;
    }

    @PostMapping("/{id}/deletar")
    public String deletarAgendamento(@PathVariable String id) {
        agendamentoService.deleteById(id);
        return "redirect:/admin";
    }
}
