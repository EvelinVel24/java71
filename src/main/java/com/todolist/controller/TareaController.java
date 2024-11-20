package main.java.com.todolist.controller;

import com.todolist.model.Tarea;
import com.todolist.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Tarea> tareas = tareaService.findAll();
        List<Tarea> tareasCompletadas = tareas.stream()
                .filter(Tarea::isCompletado)
                .collect(Collectors.toList());
        List<Tarea> tareasIncompletas = tareas.stream()
                .filter(t -> !t.isCompletado())
                .collect(Collectors.toList());

        model.addAttribute("tareasCompletadas", tareasCompletadas);
        model.addAttribute("tareasIncompletas", tareasIncompletas);
        return "home";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Tarea tarea) {
        tareaService.save(tarea);
        return "redirect:/home";
    }

    @PostMapping("/done")
    public String done(@RequestParam Long id) {
        Tarea tarea = tareaService.findAll().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (tarea != null) {
            tarea.setCompletado(true);
            tareaService.save(tarea);
        }
        return "redirect:/home";
    }
}


