package es.lag.gestionapp.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Clientes {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellidos;

    private String direccion;

    private String poblacion;

    private String pais;

    private Integer codZip;

    private Integer telefono;

    private Integer movil;

    private String email;

    private String comentarios;

    private String imagen;


    @OneToMany(mappedBy = "clientes", cascade = {CascadeType.MERGE,CascadeType.DETACH}, orphanRemoval = false)
    private List<PedidosClientes> pedidosClientes = new ArrayList<>();


}
