package es.lag.gestionapp.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LineasPedido {

    @Id
    @GeneratedValue
    private Long id;

    private Integer cantidad;

    private String descripcion;


    @ManyToOne(optional = false)
    @JoinColumn(name = "pedidos_clientes_id", nullable = false)
    private PedidosClientes pedidosClientes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productos_id", nullable = false)
    private Productos productos;


}