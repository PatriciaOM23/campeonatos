package es.fplumara.dam1.campeonato.repository;

import es.fplumara.dam1.campeonato.model.Resultado;

import java.util.List;
import java.util.Optional;

public interface ResultadoRepository {
    public void save(Resultado r);
    public Optional<Resultado> findById(String id);
    public List<Resultado> listAll();
    boolean existByPruebaYDeportista(String idPrueba,String idDeportista);
}
