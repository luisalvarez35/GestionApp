package es.lag.gestionapp.controller;


import es.lag.gestionapp.model.Facturas;
import es.lag.gestionapp.model.LineasFactura;
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

public class FacturasController {

    @Autowired
    private FacturasService facturasService;

    @Autowired
    private LineasFacturaService lineasFacturaService;

    @Autowired
    private PedidosClientesService pedidosClientesService;

    @Autowired
    private LineasPedidoService lineasPedidoService;


    @GetMapping("/facturas")
    public String facturas (Model model) {
        model.addAttribute("facturas", facturasService.findAll());


        return "facturas/facturas";
    }

    @GetMapping("facturas/nuevo/{id}")
    public String nuevaFactura(@PathVariable("id") Long id, Model model) {

        PedidosClientes pedido = pedidosClientesService.findById(id);    //tenemos el pedido
        List<LineasPedido> lineasPedido = lineasPedidoService.findByIdPedido(id); //tenemos array con lineas de pedido

        Facturas factura = new Facturas();

        //Copiamos datos a objeto factura de pedido

        factura.setId(id);
        factura.setFecha(pedido.getFecha());
        factura.setEmpleado(pedido.getEmpleados().getNombre());
        factura.setCliente(pedido.getClientes().getNombre());
        factura.setDireccionCliente(pedido.getClientes().getDireccion());
        factura.setDescripcion(pedido.getDescripcion());
        factura.setBaseImponible(pedido.getBaseImponible());
        factura.setIva(pedido.getIva());
        factura.setTotal(pedido.getTotal());


        //Salvamos factura
        facturasService.save(factura);

        //recorremos array y salvamos cada entrada

        for (int i=0;i<lineasPedido.size();i++) {

            LineasPedido lineaPedido = lineasPedido.get(i);

            LineasFactura lineaFactura = new LineasFactura();

            lineaFactura.setId(lineaPedido.getId());
            lineaFactura.setProducto(lineaPedido.getProductos().getNombre());
            lineaFactura.setCantidad(lineaPedido.getCantidad());
            lineaFactura.setDescripcion(lineaPedido.getDescripcion());
            lineaFactura.setPrecio(lineaPedido.getProductos().getPrecioVenta());
            lineaFactura.setFacturas(factura);

            lineasFacturaService.save(lineaFactura);
        }

        model.addAttribute("facturas", facturasService.findAll());

        return "facturas/facturas";
    }


    @GetMapping("facturas/borrar/{id}")
    public String borrarFactura(@PathVariable("id") Long id, Model model) {

        Facturas factura = facturasService.findById(id);

        if (factura != null) {
            facturasService.delete(factura);
        }

        return "redirect:/facturas";

    }

    @GetMapping("facturas/detalles/{id}")
    public String detallesFacturas(@PathVariable("id") Long id, Model model) {

        model.addAttribute("facturas", facturasService.findById(id));
        model.addAttribute("lineasFactura", lineasFacturaService.findByIdFactura(id));

        return "facturas/detallesFactura";


    }

    @GetMapping("facturas/imprimir/{id}")
    public String imprimirFacturas(@PathVariable("id") Long id, Model model) {

        model.addAttribute("facturas", facturasService.findById(id));
        model.addAttribute("lineasFactura", lineasFacturaService.findByIdFactura(id));

        return "facturas/facturaImprimir";


    }


}
