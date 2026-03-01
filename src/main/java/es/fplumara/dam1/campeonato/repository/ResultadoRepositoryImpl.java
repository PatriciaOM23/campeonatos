package es.fplumara.dam1.campeonato.repository;

import es.fplumara.dam1.campeonato.model.Resultado;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResultadoRepositoryImpl implements ResultadoRepository{
    private Map<String,Resultado> datos;

    public ResultadoRepositoryImpl(Map<String, Resultado> datos) {
        this.datos = datos;
    }

    @Override
    public void save(Resultado r) {
        datos.put(r.getId(),r);
    }

    @Override
    public Optional<Resultado> findById(String id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Resultado> listAll() {
        return datos.values().stream().toList();
    }

    @Override
    public boolean existByPruebaYDeportista(String idPrueba, String idDeportista) {
        return datos.values()
                .stream()
                .anyMatch(resultado -> resultado.getIdPrueba().equals(idPrueba)
                        && resultado.getIdDeportista().equals(idDeportista));
    }

}
