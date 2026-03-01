package es.fplumara.dam1.campeonato.repository;

import es.fplumara.dam1.campeonato.model.Deportista;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeportistaRepositoryImpl implements DeportistaRepository{
    private Map<String,Deportista> datos;

    public DeportistaRepositoryImpl() {
        this.datos = new HashMap<>();
    }

    @Override
    public void save(Deportista d) {
        datos.put(d.getId(), d);
    }

    @Override
    public Optional<Deportista> findById(String id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Deportista> findByPais(String pais) {

        return datos.values()
                .stream()
                .filter(deportista -> deportista.getPais().equals(pais))
                .collect(Collectors.toList());
    }

    @Override
    public List<Deportista> listAll() {
        return datos.values().stream().toList();
    }
}
