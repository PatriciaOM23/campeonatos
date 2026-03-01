package es.fplumara.dam1.campeonato.app;

import es.fplumara.dam1.campeonato.io.*;
import es.fplumara.dam1.campeonato.model.Deportista;
import es.fplumara.dam1.campeonato.model.Resultado;
import es.fplumara.dam1.campeonato.model.TipoPrueba;
import es.fplumara.dam1.campeonato.repository.DeportistaRepositoryImpl;
import es.fplumara.dam1.campeonato.repository.ResultadoRepositoryImpl;
import es.fplumara.dam1.campeonato.service.CampeonatoService;

import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Main de ejemplo para demostrar el flujo mínimo del examen (sin menú complejo).
 * Debe leer ficheros de entrada y escribir un fichero de salida.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Examen DAM1 - Campeonato deportivo (Java 21)");
        DeportistaRepositoryImpl deportistaRepository = new DeportistaRepositoryImpl();
        ResultadoRepositoryImpl resultadoRepository = new ResultadoRepositoryImpl();
        CampeonatoService campeonatoService = new CampeonatoService(deportistaRepository,resultadoRepository);
        ResultadoCsvIO resultadoGestorCSV = new ResultadoCsvIO();
        DeportistaCsvIO deportistaGestorCSV = new DeportistaCsvIO();
        RankingCsvWriter escritorRanking = new RankingCsvWriter();
        List<RegistroDeportistaCsv> registroDeportista = deportistaGestorCSV.leer(Path.of("data/deportistas.csv"));
        for(RegistroDeportistaCsv i : registroDeportista) {
            deportistaRepository.save(new Deportista(i.id(),i.nombre(),i.pais()));
        }
        List<RegistroResultadoCsv> resultadoCsv = resultadoGestorCSV.leer(Path.of("data/resultados.csv"));
        for(RegistroResultadoCsv r : resultadoCsv){
        TipoPrueba tipoPrueba = null;
            switch (r.tipoPrueba()){
                case "CARRERA" -> tipoPrueba = TipoPrueba.CARRERA;
                case "SALTO" -> tipoPrueba = TipoPrueba.SALTO;
                case "LANZAMIENTO" -> tipoPrueba = TipoPrueba.LANZAMIENTO;
            }

            resultadoRepository.save(new Resultado(r.id(),r.idPrueba(),tipoPrueba, r.idDeportista(),r.posicion()));
        }

        campeonatoService.paisesParticipantes().forEach(System.out::println);
        campeonatoService.ranking().forEach(System.out::println);
        campeonatoService.resultadosDePais("ES");

        List <Deportista> deportistas = deportistaRepository.listAll();
        List<Resultado> resultados = resultadoRepository.listAll();
        List<RegistroRankingCsv> listRegistrosRanking = new ArrayList<>();
        for(Resultado r : resultados) {
            for (Deportista d : deportistas) {
               listRegistrosRanking.add( new RegistroRankingCsv(d.getId(), d.getNombre(), d.getPais(),r.getPuntos()));
            }
                escritorRanking.escribir(Path.of("data/ranking.csv"),listRegistrosRanking);
        }
    }
}
