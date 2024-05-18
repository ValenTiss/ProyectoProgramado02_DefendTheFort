package com.tec.estudiantes;

import javax.accessibility.AccessibleTableModelChange;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;

public class ThreadZombie extends Thread implements Serializable {
    private boolean sigueVivo = true;
    private boolean estaAtacando = false;

    private boolean encontroObjetivo = false;

    private Juego partida ;
    private Zombie zombieEnAccion;
    private JLabel zombieEnPantalla;

    private JLabel vida;

    private JPanel pnlVida;
    private JLabel nexoP;

    private Arma nexo;

    private ArrayList<Arma> armas;
    private Arma objetivo;
    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, name)}.
     */
    public ThreadZombie(Juego partida, Zombie zombieEnAccion, JLabel zombieEnPantalla, ArrayList<Arma> armas, JPanel pnlVida,JLabel vida,Arma nexo,JLabel nexoP) {
        this.partida = partida;
        this.zombieEnAccion = zombieEnAccion;
        this.zombieEnPantalla = zombieEnPantalla;
        this.pnlVida = pnlVida;
        this.vida = vida;
        this.armas = armas;
        this.nexo = nexo;
        this.nexoP = nexoP;
    }

    public void run(){
        while((sigueVivo) && (!encontroObjetivo)){
            try{
                if (zombieEnAccion.getVida() <= 0){
                    sigueVivo = false;
                    partida.eliminarComponente(zombieEnPantalla);
                    partida.eliminarComponente(pnlVida);

                    return ;
                }

                Arma atacar = zombieEnAccion.vigilando(armas);
                if (atacar != null)
                {
                    if(atacar.getTipo().equals(DEFENSA.MINA)){
                        objetivo = null;
                    }else{
                        encontroObjetivo = true;
                        objetivo = atacar;
                    }

                }else{
                    zombieEnAccion.mover(nexo.getPosicionX(),nexo.getPosicionY());
                    zombieEnPantalla.setLocation((int)zombieEnAccion.getPosicionX(),(int)zombieEnAccion.getPosicionY());
                    vida.setText(String.valueOf(zombieEnAccion.getVida()));
                    pnlVida.setLocation((int)zombieEnAccion.getPosicionX(),(int)zombieEnAccion.getPosicionY() - 10);
                }
                sleep(500);

            }catch (InterruptedException ex){

            }
        }
        while((sigueVivo)&&(encontroObjetivo)&&(!estaAtacando)){
            try {
                if (zombieEnAccion.getVida() <= 0){
                    sigueVivo = false;
                    partida.eliminarComponente(zombieEnPantalla);
                    partida.eliminarComponente(pnlVida);
                    return ;
                }
                double distancia = Math.sqrt(Math.pow((Math.abs(objetivo.getPosicionY()-zombieEnAccion.getPosicionY())),2)+Math.pow((Math.abs(objetivo.getPosicionX()-zombieEnAccion.getPosicionX())),2));

                if(distancia <= zombieEnAccion.getRango()){
                    estaAtacando = true;
                }else{

                    zombieEnAccion.mover(objetivo.getPosicionX(),objetivo.getPosicionY());
                    zombieEnPantalla.setLocation((int)zombieEnAccion.getPosicionX(),(int)zombieEnAccion.getPosicionY());
                    vida.setText(String.valueOf(zombieEnAccion.getVida()));
                    pnlVida.setLocation((int)zombieEnAccion.getPosicionX(),(int)zombieEnAccion.getPosicionY() - 10);
                }


                sleep(500);

            }catch (InterruptedException ex){

            }
        }

        while((sigueVivo)&&(encontroObjetivo)&&(estaAtacando)){
            try {
                if (zombieEnAccion.getVida() <= 0){
                    sigueVivo = false;
                    partida.eliminarComponente(zombieEnPantalla);
                    partida.eliminarComponente(pnlVida);
                    return ;
                }

                JLabel atacandolbl = new JLabel("ATACANDO");

                if(objetivo.getVida() <= 0){
                    estaAtacando = false;
                    encontroObjetivo = false;
                    armas.remove(objetivo);
                    objetivo = null;
                    this.run();

                }else{
                    atacandolbl.setSize(25,25);
                    atacandolbl.setLocation(zombieEnAccion.getPosicionX(),zombieEnAccion.getPosicionY());
                    vida.setText(String.valueOf(zombieEnAccion.getVida()));
                    partida.getContentPane().add(atacandolbl);
                    if(zombieEnAccion.getTipo().equals(ENEMIGO.CHOQUE)){
                        zombieEnAccion.atacar(armas);
                        sigueVivo = false;
                    }else{
                        zombieEnAccion.atacar(objetivo);
                    }
                }
                sleep(1000);
            }catch (InterruptedException ex){

            }
        }
    }

    public boolean isSigueVivo() {
        return sigueVivo;
    }

    public void setSigueVivo(boolean sigueVivo) {
        this.sigueVivo = sigueVivo;
    }
}
