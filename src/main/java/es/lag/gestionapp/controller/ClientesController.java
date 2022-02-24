package es.lag.gestionapp.controller;

import es.lag.gestionapp.model.Clientes;
import es.lag.gestionapp.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class ClientesController {

        @Autowired
        private ClientesService clientesService;


        @GetMapping("/clientes")
        public String clientes (Model model) {
            model.addAttribute("clientes", clientesService.findAll());

            return "clientes/clientes";
        }

        @GetMapping("clientes/nuevo")
        public String nuevoCliente(Model model) {
            model.addAttribute("clientes", new Clientes());

            return "clientes/formClientes";
        }

        @PostMapping("clientes/nuevo/submit")
        public String submitNuevoCliente(@Valid Clientes clientes, BindingResult bindingResult, Model model) {

            if (bindingResult.hasErrors()) {
                return "clientes/formClientes";
            } else {
                clientesService.save(clientes);
                model.addAttribute("clientes", clientesService.findAll());
                return "clientes/clientes";


            }

        }

        @GetMapping("clientes/editar/{id}")
        public String editarClientes(@PathVariable("id") Long id, Model model) {

            Clientes clientes = clientesService.findById(id);

            if (clientes != null) {
                model.addAttribute("clientes", clientes);
                return "clientes/formClientes";
            } else {
                return "redirect:/clientes";
            }

        }
        @GetMapping("clientes/borrar/{id}")
        public String borrarCliente(@PathVariable("id") Long id, Model model) {

            Clientes clientes = clientesService.findById(id);

            if (clientes != null) {
                clientesService.delete(clientes);
            }

            return "redirect:/clientes";

        }





}
