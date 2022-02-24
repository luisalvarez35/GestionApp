package es.lag.gestionapp.controller;

import es.lag.gestionapp.model.Productos;
import es.lag.gestionapp.model.Proveedores;
import es.lag.gestionapp.service.CategoriasService;
import es.lag.gestionapp.service.ProductosService;
import es.lag.gestionapp.service.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller

public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @Autowired
    private CategoriasService categoriasService;

    @Autowired
    private ProveedoresService proveedoresService;


    @GetMapping("/productos")
    public String productos (Model model) {
        model.addAttribute("productos", productosService.findAll());

        return "productos/productos";
    }

    @GetMapping("productos/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("productos", new Productos());
        model.addAttribute("categorias", categoriasService.findAll());
        model.addAttribute("proveedores", proveedoresService.findAll());

        return "productos/formProductos";
    }

    @PostMapping("productos/nuevo/submit")
    public String submitNuevoProducto(@Valid Productos productos, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", categoriasService.findAll());
            model.addAttribute("proveedores", proveedoresService.findAll());
            return "productos/formProductos";
        } else {
            productosService.save(productos);
            model.addAttribute("productos", productosService.findAll());
            return "productos/productos";

        }

    }

    @GetMapping("productos/editar/{id}")
    public String editarProductos(@PathVariable("id") Long id, Model model) {

        Productos productos = productosService.findById(id);

        if (productos != null) {
            model.addAttribute("productos", productos);
            /*model.addAttribute("cat", categoriasService.findAll());
            model.addAttribute("prov", proveedoresService.findAll());*/
            return "productos/editProductos";
        } else {
            return "redirect:/productos";
        }

    }

    @PostMapping("productos/edit/submit")
    public String submitEditProducto(@Valid Productos productos, Model model) {

            productosService.save(productos);

            model.addAttribute("productos", productosService.findAll());

            return "redirect:/productos";

    }

    @GetMapping("productos/borrar/{id}")
    public String borrarProducto(@PathVariable("id") Long id, Model model) {

        Productos productos = productosService.findById(id);

        if (productos != null) {
            productosService.delete(productos);
        }

        return "redirect:/productos";

    }

}
