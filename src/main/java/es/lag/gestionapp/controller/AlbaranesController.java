package es.lag.gestionapp.controller;


import es.lag.gestionapp.model.Albaranes;
import es.lag.gestionapp.model.LineasAlbaran;
import es.lag.gestionapp.model.LineasPedido;
import es.lag.gestionapp.model.PedidosClientes;
import es.lag.gestionapp.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller

public class AlbaranesController {

    @Autowired
    private AlbaranesService albaranesService;

    @Autowired
    private LineasAlbaranService lineasAlbaranService;

    @Autowired
    private PedidosClientesService pedidosClientesService;

    @Autowired
    private LineasPedidoService lineasPedidoService;


    @GetMapping("/albaranes")
    public String albaranes (Model model) {
        model.addAttribute("albaranes", albaranesService.findAll());


        return "albaranes/albaranes";
    }

    @GetMapping("albaranes/nuevo/{id}")
    public String nuevaAlbaran(@PathVariable("id") Long id, Model model) {

        PedidosClientes pedido = pedidosClientesService.findById(id);    //tenemos el pedido
        List<LineasPedido> lineasPedido = lineasPedidoService.findByIdPedido(id); //tenemos array con lineas de pedido

        Albaranes albaran = new Albaranes();

        //Copiamos datos a objeto albaran de pedido

        albaran.setId(id);
        albaran.setFecha(pedido.getFecha());
        albaran.setEmpleado(pedido.getEmpleados().getNombre());
        albaran.setCliente(pedido.getClientes().getNombre());
        albaran.setDireccionCliente(pedido.getClientes().getDireccion());
        albaran.setDescripcion(pedido.getDescripcion());
        albaran.setBaseImponible(pedido.getBaseImponible());
        albaran.setIva(pedido.getIva());
        albaran.setTotal(pedido.getTotal());


        //Salvamos albaran
        albaranesService.save(albaran);

        //recorremos array y salvamos cada entrada

        for (int i=0;i<lineasPedido.size();i++) {

            LineasPedido lineaPedido = lineasPedido.get(i);

            LineasAlbaran lineaAlbaran = new LineasAlbaran();

            lineaAlbaran.setId(lineaPedido.getId());
            lineaAlbaran.setProducto(lineaPedido.getProductos().getNombre());
            lineaAlbaran.setCantidad(lineaPedido.getCantidad());
            lineaAlbaran.setDescripcion(lineaPedido.getDescripcion());
            lineaAlbaran.setPrecio(lineaPedido.getProductos().getPrecioVenta());
            lineaAlbaran.setAlbaranes(albaran);

            lineasAlbaranService.save(lineaAlbaran);

        }

        model.addAttribute("albaranes", albaranesService.findAll());

        return "albaranes/albaranes";
    }

    @GetMapping("albaranes/borrar/{id}")
    public String borrarAlbaran(@PathVariable("id") Long id, Model model) {

        Albaranes albaran = albaranesService.findById(id);

        if (albaran != null) {
            albaranesService.delete(albaran);
        }

        return "redirect:/albaranes";

    }

    @GetMapping("albaranes/detalles/{id}")
    public String detallesAlbaran(@PathVariable("id") Long id, Model model) {

        model.addAttribute("albaranes", albaranesService.findById(id));
        model.addAttribute("lineasAlbaran", lineasAlbaranService.findByIdAlbaran(id));

        return "albaranes/detallesAlbaran";

    }

    @GetMapping("albaranes/imprimir/{id}")
    public String imprimirAlbaran(@PathVariable("id") Long id, Model model) {

        model.addAttribute("albaranes", albaranesService.findById(id));
        model.addAttribute("lineasAlbaran", lineasAlbaranService.findByIdAlbaran(id));

        return "albaranes/albaranImprimir";


    }


}

