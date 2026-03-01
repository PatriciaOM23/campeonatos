package es.fplumara.dam1.campeonato.service;

import es.fplumara.dam1.campeonato.exception.DuplicadoException;
import es.fplumara.dam1.campeonato.exception.NoEncontradoException;
import es.fplumara.dam1.campeonato.exception.OperacionNoPermitidaException;
import es.fplumara.dam1.campeonato.model.Deportista;
import es.fplumara.dam1.campeonato.model.LineaRanking;
import es.fplumara.dam1.campeonato.model.Resultado;
import es.fplumara.dam1.campeonato.repository.DeportistaRepository;
import es.fplumara.dam1.campeonato.repository.ResultadoRepository;

import java.util.List;
import java.util.Set;

public class CampeonatoService {
    private DeportistaRepository deportistaRepository;
    private ResultadoRepository resultadoRepository;

    public CampeonatoService(DeportistaRepository deportistaRepository, ResultadoRepository resultadoRepository) {
        this.deportistaRepository = deportistaRepository;
        this.resultadoRepository = resultadoRepository;
    }
    public void registrarDeportista(Deportista d){
        if(d == null || d.getId().isBlank() || d.getPais().isBlank() || d.getNombre().isBlank() ){
            throw new IllegalArgumentException("Datos vacíos al registrar un deportista.");
        }
        if(deportistaRepository.findById(d.getId()).isPresent()){
            throw new DuplicadoException("Ese deportista ya está registrado.");
        }
        deportistaRepository.save(d);
    }
    public void registrarResultado (Resultado r){
        if(r == null || r.getId().isBlank() || r.getIdDeportista().isBlank() || r.getIdPrueba().isBlank()){
            throw new IllegalArgumentException("Datos vacíos al registrar un resultado.");
        }
        if(r.getTipoPrueba().toString().isBlank()){
            throw new IllegalArgumentException("Indica el tipo de prueba realizada.");
        }
        if(r.getPosicion() <= 0){
            throw new IllegalArgumentException("La posición no puede ser menor a cero.");
        }
        if(resultadoRepository.findById(r.getId()).isPresent()){
            throw new DuplicadoException("Ese resultado ya está registrado.");
        }
        if(deportistaRepository.findById(r.getIdDeportista()).isEmpty()){
            throw new NoEncontradoException("El deportista indicado no ha sido encontrado.");
        }
        if(resultadoRepository.existByPruebaYDeportista(r.getIdPrueba(),r.getIdDeportista())){
            throw new OperacionNoPermitidaException("Solo se puede tener 1 resultado por prueba");
        }
        resultadoRepository.save(r);
    }
    public List<LineaRanking> rankingList(){
        return List.of();
    }
    public List<Resultado> resultadosDePais(){
        return List.of();
    }
    public Set<String> paisesParticipantes(){
        return Set.of();
    }
}
