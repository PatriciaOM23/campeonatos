package es.fplumara.dam1.campeonato.model;

import java.util.stream.Stream;

public class Resultado implements Puntuable{
    private String id;
    private String idPrueba;
    private TipoPrueba tipoPrueba;
    private String idDeportista;
    private int posicion;

    public Resultado(String id, String idPrueba, TipoPrueba tipoPrueba, String idDeportista, int posicion) {
        this.id = id;
        this.idPrueba = idPrueba;
        this.tipoPrueba = tipoPrueba;
        this.idDeportista = idDeportista;
        this.posicion = posicion;
    }

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

    @Override
    public String toString() {
        return "Resultado{" +
                "id='" + id + '\'' +
                ", idPrueba='" + idPrueba + '\'' +
                ", tipoPrueba=" + tipoPrueba +
                ", idDeportista='" + idDeportista + '\'' +
                ", posicion=" + posicion +
                '}';
    }

    @Override
    public int getPuntos() {
        switch (getPosicion()){
            case 1 -> {
                return 5;
            }
            case 2 -> {
                return 3;
            }
            case 3 -> {
                return 1;
            }
            default -> {
                return 0;
            }
        }
    }
}
