package com.tec.estudiantes;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Partida implements Serializable {

    BDEventos eventos;
    Evento evt;

    private Map<Zombie,JLabel> zombiesPresentes;

    private Map<Arma,JLabel> armasPresentes;

    private boolean lvlIsActivePnl;

    private boolean armamentoIsActivePnl;

    private boolean seleccionoArma;

    private Arma armaAgregar;

    private ArrayList<Zombie> ejercitoZombie;
    private ArrayList<Arma> armasDelUsuario;

    private Map<Arma,ArrayList<JTextField>> stadisticsArmas ;

    Integer lvl;

    public Partida(Evento evt,BDEventos eventos){
        this.eventos = eventos;
        this.evt = evt;
        Map<Espacio, JPanel> espaciosFuerte = new HashMap<>();
        Map<Zombie,JLabel> zombiesPresentes = new HashMap<>();
        Map<Arma,JLabel> armasPresentes = new HashMap<>();
        lvlIsActivePnl = false;
        armamentoIsActivePnl = false;
        seleccionoArma = false;
        ArrayList<Zombie> ejercitoZombie = new ArrayList<>();
        ArrayList<Arma> armasDelUsuario = new ArrayList<>();
        Map<Arma,ArrayList<JTextField>> stadisticsArmas = new HashMap<>();
        lvl = 1;

    }

    public void guardarDatos(boolean lvlIsActivePnl,boolean armamentoIsActivePnl,boolean seleccionoArma,
                             ArrayList<Zombie> ejercitoZombie,ArrayList<Arma> armasDelUsuario,
                             Map<Arma,ArrayList<JTextField>> stadisticsArmas,Integer lvl){


        this.lvlIsActivePnl  = lvlIsActivePnl ;

        this.armamentoIsActivePnl = armamentoIsActivePnl;

        this.seleccionoArma = seleccionoArma;

        this.armaAgregar = armaAgregar;

        this.ejercitoZombie = ejercitoZombie;
        this.armasDelUsuario = armasDelUsuario;

        this.stadisticsArmas = stadisticsArmas;
        this.lvl = lvl;
        eventos.getSpecificUser(evt.getUser(),evt.getPassword()).setPda(this);
        eventos.guardar();
    }

    public void start(){
        JFrame juego = new Juego(this,zombiesPresentes,
                armasPresentes,lvlIsActivePnl,armamentoIsActivePnl,seleccionoArma,
                ejercitoZombie,armasDelUsuario, stadisticsArmas,lvl);
        juego.setVisible(true);
    }
}
