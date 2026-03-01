package es.fplumara.dam1.campeonato.model;

import java.util.stream.Stream;

public class Resultado {
    private String id;
    private String idPrueba;
    private TipoPrueba tipoPrueba;
    private String idDeportista;
    private int posicion;

    public String getId() {
        return id;
    }

    public String getIdPrueba() {
        return idPrueba;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public String getIdDeportista() {
        return idDeportista;
    }

    public int getPosicion() {
        return posicion;
    }
}
