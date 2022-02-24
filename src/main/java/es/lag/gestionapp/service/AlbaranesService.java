package es.lag.gestionapp.service;

import es.lag.gestionapp.model.Albaranes;
import es.lag.gestionapp.repository.AlbaranesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbaranesService {

    @Autowired
    private AlbaranesRepository repositorio;

    public List<Albaranes> findAll() {
        return repositorio.findAll();
    }

    public Albaranes save(Albaranes albaranes) {
        return repositorio.save(albaranes);
    }

    public Albaranes findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Albaranes delete(@NotNull Albaranes albaranes) {
        Albaranes result = findById(albaranes.getId());
        repositorio.delete(result);
        return result;
    }
}
