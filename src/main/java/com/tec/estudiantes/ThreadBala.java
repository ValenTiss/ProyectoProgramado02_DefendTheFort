package com.tec.estudiantes;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ThreadBala extends Thread implements Serializable {
    private boolean encontroObjetivo = false;
    private Juego partida ;
    private Zombie zombieEnAccion;
    private ImageIcon imagenBala = partida.scaleImage(new ImageIcon(System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/bala.png"),20,20) ;
    private JLabel lblBala = new JLabel(imagenBala);

    private int Xinicio;
    private int Yinicio;
    private Arma objetivo;
    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, name)}.
     */
    public ThreadBala(Juego partida, Zombie zombieEnAccion,Arma objetivo) {
        this.partida = partida;
        this.zombieEnAccion = zombieEnAccion;
        Xinicio = objetivo.getPosicionX();
        Yinicio = objetivo.getPosicionY();
        lblBala.setSize(20,20);
        lblBala.setLocation(-1,-1);
        this.partida.agregarComponente(lblBala);
        this.objetivo = objetivo;
    }

    private void mover(int zombieX, int zombieY) {
        double angulo;
        int opuesto = Math.abs(zombieY - this.Yinicio);
        int adyacente = Math.abs(zombieX - this.Xinicio);
        angulo = Math.toDegrees(Math.atan2(opuesto, adyacente));
        if ((this.Yinicio < zombieY) && (Xinicio < zombieX)) {
            Xinicio -= Math.round(Math.cos(angulo) * 2);
            Yinicio -= Math.round(Math.sin(angulo) * 2);
        } else if ((Yinicio > zombieY) && (Xinicio > zombieX)) {
            Xinicio += Math.round(Math.cos(angulo) * 2);
            Yinicio += Math.round(Math.sin(angulo) * 2);
        } else if (Yinicio > zombieY) {
            Xinicio += Math.round(Math.cos(angulo) * 2);
            Yinicio -= Math.round(Math.sin(angulo) * 2);
        } else {
            Xinicio -= Math.round(Math.cos(angulo) * 2);
            Yinicio += Math.round(Math.sin(angulo) * 2);
        }

    }

    public void run() {
        while ((!encontroObjetivo)) {
            try {
                double distancia = Math.sqrt(Math.pow((Math.abs(Yinicio-zombieEnAccion.getPosicionY())),2)+Math.pow((Math.abs(Xinicio-zombieEnAccion.getPosicionX())),2));
                if(distancia <= 50){
                    partida.eliminarComponente(lblBala);
                    encontroObjetivo = true;
                } else
                {
                    mover(zombieEnAccion.getPosicionX(), zombieEnAccion.getPosicionY());
                    lblBala.setLocation(Xinicio, Yinicio);
                }
                sleep(15);
            } catch (InterruptedException ex) {

            }
        }

    }
}
