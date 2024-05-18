package com.tec.estudiantes;

import java.io.Serializable;
import java.util.ArrayList;

public class Evento implements Serializable {

    private BDEventos eventos;
    private String user;
    private String password;
    private Partida pda;


    public Evento(String user, String password, BDEventos eventos) {
        this.eventos = eventos;
        this.user = user;
        this.password = password;
        pda = new Partida(this,eventos);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Partida getJuegoAsignado() {
        return pda;
    }

    public void setPda(Partida pda) {
        this.pda = pda;
    }
}
