package es.lag.gestionapp.service;

import es.lag.gestionapp.model.Clientes;
import es.lag.gestionapp.repository.ProveedoresRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.lag.gestionapp.model.Proveedores;

import java.util.List;

@Service
public class ProveedoresService {

    @Autowired
    private ProveedoresRepository repositorio;

    public List<Proveedores> findAll() {
        return repositorio.findAll();
    }

    public Proveedores save(Proveedores proveedores) {
        return repositorio.save(proveedores);
    }

    public Proveedores findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Proveedores delete(@NotNull Proveedores Proveedores) {
        Proveedores result = findById(Proveedores.getId());
        repositorio.delete(result);
        return result;
    }
}