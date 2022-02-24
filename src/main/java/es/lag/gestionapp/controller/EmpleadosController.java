package es.lag.gestionapp.controller;

import es.lag.gestionapp.model.Empleados;
import es.lag.gestionapp.service.EmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class EmpleadosController {

    @Autowired
    private EmpleadosService empleadosService;


    @GetMapping("/empleados")
    public String empleados (Model model) {
        model.addAttribute("empleados", empleadosService.findAll());

        return "empleados/empleados";
    }

    @GetMapping("empleados/nuevo")
    public String nuevoEmpleado(Model model) {
        model.addAttribute("empleados", new Empleados());

        return "empleados/formEmpleados";
    }

    @PostMapping("empleados/nuevo/submit")
    public String submitNuevoEmpleado(@Valid Empleados empleados, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "empleados/formEmpleados";
        } else {
            empleadosService.save(empleados);
            model.addAttribute("empleados", empleadosService.findAll());
            return "empleados/empleados";


        }

    }

    @GetMapping("empleados/editar/{id}")
    public String editarEmpleados(@PathVariable("id") Long id, Model model) {

        Empleados empleados = empleadosService.findById(id);

        if (empleados != null) {
            model.addAttribute("empleados", empleados);
            return "empleados/formEmpleados";
        } else {
            return "redirect:/empleados";
        }

    }
    @GetMapping("empleados/borrar/{id}")
    public String borrarEmpleado(@PathVariable("id") Long id, Model model) {

        Empleados empleados = empleadosService.findById(id);

        if (empleados != null) {
            empleadosService.delete(empleados);
        }

        return "redirect:/empleados";

    }







}
