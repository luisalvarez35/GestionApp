package es.lag.gestionapp.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Categorias {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "categorias", cascade = {CascadeType.MERGE}, orphanRemoval = false)
    private List<Productos> productos = new ArrayList<>();
}