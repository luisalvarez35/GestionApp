package es.lag.gestionapp.controller;

import es.lag.gestionapp.model.Proveedores;
import es.lag.gestionapp.service.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;


    @GetMapping("/proveedores")
    public String proveedores (Model model) {
        model.addAttribute("proveedores", proveedoresService.findAll());

        return "proveedores/proveedores";
    }

    @GetMapping("proveedores/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("proveedores", new Proveedores());

        return "proveedores/formProveedores";
    }

    @PostMapping("proveedores/nuevo/submit")
    public String submitNuevoCliente(@Valid Proveedores proveedores, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "proveedores/formProveedores";
        } else {
            proveedoresService.save(proveedores);
            model.addAttribute("proveedores", proveedoresService.findAll());
            return "proveedores/proveedores";


        }

    }

    @GetMapping("proveedores/editar/{id}")
    public String editarProveedores(@PathVariable("id") Long id, Model model) {

        Proveedores proveedores = proveedoresService.findById(id);

        if (proveedores != null) {
            model.addAttribute("proveedores", proveedores);
            return "proveedores/formProveedores";
        } else {
            return "redirect:/proveedores";
        }

    }
    @GetMapping("proveedores/borrar/{id}")
    public String borrarCliente(@PathVariable("id") Long id, Model model) {

        Proveedores proveedores = proveedoresService.findById(id);

        if (proveedores != null) {
            proveedoresService.delete(proveedores);
        }

        return "redirect:/proveedores";

    }





}
