package es.lag.gestionapp.repository;

import es.lag.gestionapp.model.LineasAlbaran;
import es.lag.gestionapp.model.LineasFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineasAlbaranRepository extends JpaRepository<LineasAlbaran, Long> {

    @Query("select l from LineasAlbaran l where l.albaranes.id = ?1")
    public List<LineasAlbaran> findByIdAlbaran(Long id);
}
