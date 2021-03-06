package es.lag.gestionapp.service;

import es.lag.gestionapp.model.Empleados;
import es.lag.gestionapp.repository.EmpleadosRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmpleadosService {

    @Autowired
    private EmpleadosRepository repositorio;

    public List<Empleados> findAll() {
        return repositorio.findAll();
    }

    public Empleados save(Empleados empleados) {
        return repositorio.save(empleados);
    }

    public Empleados findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Empleados delete(@NotNull Empleados Empleados) {
        Empleados result = findById(Empleados.getId());
        repositorio.delete(result);
        return result;
    }
}
