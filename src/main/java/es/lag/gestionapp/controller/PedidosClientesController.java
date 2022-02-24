package es.lag.gestionapp.controller;


import es.lag.gestionapp.model.Clientes;
import es.lag.gestionapp.model.Empleados;
import es.lag.gestionapp.model.PedidosClientes;
import es.lag.gestionapp.service.ClientesService;
import es.lag.gestionapp.service.EmpleadosService;
import es.lag.gestionapp.service.LineasPedidoService;
import es.lag.gestionapp.service.PedidosClientesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class PedidosClientesController {

    @Autowired
    private PedidosClientesService pedidosClientesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private EmpleadosService empleadosService;

    @Autowired
    private LineasPedidoService lineasPedidoService;


    @GetMapping("/pedidosClientes")
    public String pedidosClientes (Model model) {
        model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

        return "pedidosClientes/pedidosClientes";
    }


    @GetMapping("pedidosClientes/nuevo")
    public String nuevoPedidoClientes(Model model) {
        model.addAttribute("pedidosClientes", new PedidosClientes());
        model.addAttribute("clientes", clientesService.findAll());
        model.addAttribute("empleados", empleadosService.findAll());

        return "pedidosClientes/formPedidosClientes";
    }

    @PostMapping("pedidosClientes/nuevo/submit")
    public String submitNuevoPedidoClientes(@Valid PedidosClientes pedidosClientes, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pedidosClientes", pedidosClientesService.findAll());
            model.addAttribute("clientes", clientesService.findAll());
            model.addAttribute("empleados", empleadosService.findAll());

            return "pedidosClientes/formPedidosClientes";
        } else {
            pedidosClientesService.save(pedidosClientes);
            model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

            return "redirect:/pedidosClientes";

        }

    }

    @GetMapping("pedidosClientes/editar/{id}")
    public String editarPedidosClientes(@PathVariable("id") Long id, Model model) {

        PedidosClientes pedidosClientes = pedidosClientesService.findById(id);

        if (pedidosClientes != null) {
            model.addAttribute("pedidosClientes", pedidosClientes);

            return "pedidosClientes/editPedidosClientes";
        } else {
            return "redirect:/pedidosClientes";
        }

    }

    @PostMapping("pedidosClientes/edit/submit")
    public String submitNuevoPedidoClientes(@Valid PedidosClientes pedidosClientes, Model model) {


        float total = (float)( pedidosClientes.getBaseImponible() + (pedidosClientes.getBaseImponible() * (pedidosClientes.getIva()*0.01)) );
        pedidosClientes.setTotal(total);

            pedidosClientesService.save(pedidosClientes);
            model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

            return "redirect:/pedidosClientes";

        }

    @GetMapping("pedidosClientes/borrar/{id}")
    public String borrarPedidoClientes(@PathVariable("id") Long id, Model model) {

        PedidosClientes pedidosClientes = pedidosClientesService.findById(id);

        if (pedidosClientes != null) {
            pedidosClientesService.delete(pedidosClientes);
        }

        return "redirect:/pedidosClientes";

    }

    @GetMapping("pedidosClientes/detalles/{id}")
    public String detallesPedidosClientes(@PathVariable("id") Long id, Model model) {

        model.addAttribute("idActual", id);
        model.addAttribute("lineasPedido", lineasPedidoService.findByIdPedido(id));

        return "pedidosClientes/detallesPedido";


    }



}
