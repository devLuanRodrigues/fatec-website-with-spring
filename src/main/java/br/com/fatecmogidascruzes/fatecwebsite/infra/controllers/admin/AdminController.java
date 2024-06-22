package br.com.fatecmogidascruzes.fatecwebsite.infra.controllers.admin;

import br.com.fatecmogidascruzes.fatecwebsite.domain.model.agendamento.Agendamento;
import br.com.fatecmogidascruzes.fatecwebsite.domain.model.agendamento.AgendamentoMapper;
import br.com.fatecmogidascruzes.fatecwebsite.service.AgendamentoService;
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
    private AgendamentoService agendamentoService;

    @GetMapping
    public ModelAndView listarAgendamentos() {
        ModelAndView model = new ModelAndView("/pages/admin/index");

        List<Agendamento> agendamentos = agendamentoService.findAll();

        model.addObject("agendamentos", agendamentos.stream().sorted(Comparator.comparing(Agendamento::getData))
                .map(AgendamentoMapper::toDTO)
                .toList());

        return model;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/pages/login/index");
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento) {
        agendamentoService.save(agendamento);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editarAgendamento(@PathVariable String id) {
        ModelAndView model = new ModelAndView("/pages/admin/index");

        List<Agendamento> agendamentos = agendamentoService.findAll();

        model.addObject("agendamentos", agendamentos.stream().sorted(Comparator.comparing(Agendamento::getData))
                .map(AgendamentoMapper::toDTO)
                .toList());

        model.addObject("agendamento", agendamentoService.findById(id).orElse(new Agendamento()));
        model.addObject("editarEvento", true);
        return model;
    }

    @PostMapping("/{id}/deletar")
    public String deletarAgendamento(@PathVariable String id) {
        agendamentoService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/confirmar")
    public String confirmarAgendamento(@PathVariable String id, @ModelAttribute Agendamento agendamento) {
        agendamentoService.edit(id, agendamento);

        return "redirect:/admin";
    }
}
