package es.lag.gestionapp.controller;

import es.lag.gestionapp.model.Categorias;
import es.lag.gestionapp.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;


    @GetMapping("/categorias")
    public String categorias (Model model) {
        model.addAttribute("categorias", categoriasService.findAll());

        return "categorias/categorias";
    }

    @GetMapping("categorias/nuevo")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categorias", new Categorias());

        return "categorias/formCategorias";
    }

    @PostMapping("categorias/nuevo/submit")
    public String submitNuevaCategorias(@Valid Categorias categorias, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "categorias/formCategorias";
        } else {
            categoriasService.save(categorias);
            model.addAttribute("categorias", categoriasService.findAll());
            return "categorias/categorias";


        }

    }

    @GetMapping("categorias/editar/{id}")
    public String editarCategorias(@PathVariable("id") Long id, Model model) {

        Categorias categorias = categoriasService.findById(id);

        if (categorias != null) {
            model.addAttribute("categorias", categorias);
            return "categorias/formCategorias";
        } else {
            return "redirect:/categorias";
        }

    }
    @GetMapping("categorias/borrar/{id}")
    public String borrarCategoria(@PathVariable("id") Long id, Model model) {

        Categorias categorias = categoriasService.findById(id);

        if (categorias != null) {
            categoriasService.delete(categorias);
        }

        return "redirect:/categorias";

    }





}
