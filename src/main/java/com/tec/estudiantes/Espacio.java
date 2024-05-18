package com.tec.estudiantes;

import java.io.Serializable;

public class Espacio implements Serializable {
    private int posicionX;
    private int posicionY;
    private Arma armaAsignada;

    private String identificador;
    private Integer numeroEnLista;

    public Espacio(final int _x, final int _y,final int numeroEnLista){
        posicionX = _x;
        posicionY = _y;
        identificador = "x:"+ _x +",y:" + _y ;
        this.numeroEnLista = numeroEnLista;
        armaAsignada = null;
    }
    public static Espacio getInstance(){
            return new Espacio(0,0,-1);
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Integer getNumeroEnLista() {
        return numeroEnLista;
    }

    public void setNumeroEnLista(Integer numeroEnLista) {
        this.numeroEnLista = numeroEnLista;
    }

    public Arma getArmaAsignada() {
        return armaAsignada;
    }

    public void setArmaAsignada(Arma armaAsignada) {
        this.armaAsignada = armaAsignada;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
