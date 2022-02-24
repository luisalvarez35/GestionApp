package es.lag.gestionapp.controller;


import com.fasterxml.jackson.annotation.JsonCreator;
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
    @JsonCreator
    public ResponseEntity<List<Productos>> listadoProductos() {

        List<Productos> productos = productosService.findAll();

        if (productos.isEmpty()) {
            return new ResponseEntity<List<Productos>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Productos>>(productos, HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("productos/rest/{id}")
    public ResponseEntity<Productos> getByIdrestProductos(@PathVariable("id") Long id) {
        Optional<Productos> productos = productosService.findOptionalById(id);
        return productos.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
