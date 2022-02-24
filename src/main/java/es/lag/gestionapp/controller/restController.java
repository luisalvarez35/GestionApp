package es.lag.gestionapp.controller;


import es.lag.gestionapp.model.Productos;
import es.lag.gestionapp.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class restController {

    @Autowired
    ProductosService productosService;

    @CrossOrigin
    @GetMapping("productos/rest")
    public List<Productos> listadoProductos() {

        return productosService.findAll();
        /*List<Productos> productos = productosService.findAll();

        return productos.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));*/
    }

    @CrossOrigin
    @GetMapping("productos/rest/{id}")
    public ResponseEntity<Productos> getByIdrestProductos(@PathVariable("id") Long id) {
        Optional<Productos> productos = productosService.findOptionalById(id);
        return productos.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
