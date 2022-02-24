package es.lag.gestionapp.service;


import es.lag.gestionapp.model.LineasAlbaran;

import es.lag.gestionapp.repository.LineasAlbaranRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LineasAlbaranService {

    @Autowired
    private LineasAlbaranRepository repositorio;

    public List<LineasAlbaran> findAll() {
        return repositorio.findAll();
    }

    public LineasAlbaran save(LineasAlbaran lineasAlbarans) {
        return repositorio.save(lineasAlbarans);
    }

    public LineasAlbaran findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public List<LineasAlbaran> findByIdAlbaran(Long id) {return repositorio.findByIdAlbaran(id);}

    public LineasAlbaran delete(@NotNull LineasAlbaran LineasAlbaran) {
        LineasAlbaran result = findById(LineasAlbaran.getId());
        repositorio.delete(result);
        return result;
    }



}