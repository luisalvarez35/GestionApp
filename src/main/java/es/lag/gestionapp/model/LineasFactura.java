package es.lag.gestionapp.model;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class LineasFactura {

    @Id
    //@GeneratedValue
    private Long id;

    private String producto;

    private Integer cantidad;

    private String descripcion;

    private Float precio;


    @ManyToOne(optional = false)
    @JoinColumn(name = "facturas_id", nullable = false)
    private Facturas facturas;
    
}