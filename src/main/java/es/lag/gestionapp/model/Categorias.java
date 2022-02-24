package es.lag.gestionapp.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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


    @OneToMany(mappedBy = "categorias", cascade = {CascadeType.MERGE})
    private List<Productos> productos = new ArrayList<>();
}
