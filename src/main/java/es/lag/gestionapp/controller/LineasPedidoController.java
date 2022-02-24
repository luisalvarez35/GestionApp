package es.lag.gestionapp.controller;



import es.lag.gestionapp.model.LineasPedido;
import es.lag.gestionapp.model.PedidosClientes;
import es.lag.gestionapp.model.Productos;
import es.lag.gestionapp.service.EmpleadosService;
import es.lag.gestionapp.service.LineasPedidoService;
import es.lag.gestionapp.service.PedidosClientesService;

import es.lag.gestionapp.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller

public class LineasPedidoController {

    @Autowired
    private LineasPedidoService lineasPedidoService;

    @Autowired
    private PedidosClientesService pedidosClientesService;

    @Autowired
    private ProductosService productosService;

    @Autowired
    private EmpleadosService empleadosService;


    @GetMapping("/lineasPedido")
    public String lineasPedido (Model model) {
        model.addAttribute("lineasPedido", lineasPedidoService.findAll());

        return "pedidosClientes/detallesPedido";
    }


    @GetMapping("lineasPedido/nuevo/{id}")
    public String nuevaLineasPedido(@PathVariable("id") Long id, Model model) {

        model.addAttribute("idActual", id);
        model.addAttribute("lineasPedido", new LineasPedido());
        model.addAttribute("productos", productosService.findAll());
        model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

        return "pedidosClientes/formLineasPedido";
    }

    @PostMapping("lineasPedido/nuevo/submit")
    public String submitNuevaLineaPedido(@Valid LineasPedido lineasPedido, BindingResult bindingResult, Model model) {

            //id del pedido
            PedidosClientes ped = lineasPedido.getPedidosClientes();
            Long id =ped.getId();

        if (bindingResult.hasErrors()) {
            model.addAttribute("lineasPedido", lineasPedidoService.findByIdPedido(id));
            model.addAttribute("productos", productosService.findAll());
            model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

            return "pedidosClientes/formLineasPedido";
        } else {
            //salvamos linea de pedido
            lineasPedidoService.save(lineasPedido);

            //actualizamos pedido

            //calculamos total de la linea
            Float total_linea = lineasPedido.getPrecio()*lineasPedido.getCantidad();

            //recuparamos pedido
            PedidosClientes pedido = pedidosClientesService.findById(id);

            //realizamos calculos

            //////////////////////////// Actualizamos baseImponible
            List<LineasPedido> lineasPedidos = lineasPedidoService.findByIdPedido(id);

            float baseTemp= 0f;

            for (int i=0;i<lineasPedidos.size();i++) {

                float precio = lineasPedidos.get(i).getPrecio();
                float cantidad = lineasPedidos.get(i).getCantidad();
                float totalLinea = precio * cantidad;

                baseTemp+=totalLinea;
            }
            pedido.setBaseImponible(baseTemp);


            float total = (float)( baseTemp + (baseTemp * (pedido.getIva()*0.01)) );
            pedido.setTotal(total);


            //actualizamos pedido
            pedidosClientesService.save(pedido);

            model.addAttribute("lineasPedido", lineasPedidoService.findByIdPedido(id));
            model.addAttribute("productos", productosService.findAll());
            model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

            return "redirect:/pedidosClientes/detalles/"+id;

        }

    }

    @GetMapping("lineasPedido/editar/{id}")
    public String editarLineasPedido(@PathVariable("id") Long id, Model model) {

        LineasPedido lineasPedido = lineasPedidoService.findById(id);

        if (lineasPedido != null) {
            model.addAttribute("lineasPedido", lineasPedido);

            return "pedidosClientes/editLineasPedido";
        } else {
            return "redirect:/pedidosClientes";
        }

    }

    @PostMapping("lineasPedido/edit/submit")
    public String submitEditLineaPedido(@Valid LineasPedido lineasPedido, Model model) {

        //id del pedido
        PedidosClientes ped = lineasPedido.getPedidosClientes();
        Long id =ped.getId();

            //salvamos linea de pedido
            lineasPedidoService.save(lineasPedido);

            //actualizamos pedido

            //calculamos total de la linea
            Float total_linea = lineasPedido.getPrecio()*lineasPedido.getCantidad();

            //recuparamos pedido
            PedidosClientes pedido = pedidosClientesService.findById(id);

            //realizamos calculos

            /////////////// Actualizamos baseImponible
            List<LineasPedido> lineasPedidos = lineasPedidoService.findByIdPedido(id);

            float baseTemp= 0f;

                for (int i=0;i<lineasPedidos.size();i++) {

                    float precio = lineasPedidos.get(i).getPrecio();
                    float cantidad = lineasPedidos.get(i).getCantidad();
                    float totalLinea = precio * cantidad;

                    baseTemp+=totalLinea;
                }
                pedido.setBaseImponible(baseTemp);


            float total = (float)( baseTemp + (baseTemp * (pedido.getIva()*0.01)) );
            pedido.setTotal(total);


            //actualizamos pedido
            pedidosClientesService.save(pedido);

            model.addAttribute("lineasPedido", lineasPedidoService.findByIdPedido(id));
            model.addAttribute("productos", productosService.findAll());
            model.addAttribute("pedidosClientes", pedidosClientesService.findAll());

            return "redirect:/pedidosClientes/detalles/"+id;

        }


    @GetMapping("lineasPedido/borrar/{id}")
    public String borrarLineaPedido(@PathVariable("id") Long id, Model model) {

        LineasPedido lineasPedido = lineasPedidoService.findById(id);
        Long idPedido = lineasPedido.getPedidosClientes().getId();

        if (lineasPedido != null) {
            lineasPedidoService.delete(lineasPedido);
        }

        return "redirect:/pedidosClientes/detalles/"+idPedido;

    }

}
