package com.tec.estudiantes;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ThreadArmas extends Thread implements Serializable {
    private boolean vigilando = true;

    private boolean sigueVivo = true;
    private boolean atacando = false;
    private Zombie zombieAtacar = null;


    private Juego partida ;

    private JLabel vidaArma;
    private Arma armaAsignada;
    private JLabel lblArma;
    private JPanel pnlVida;
    private ArrayList<Zombie> zombies;

    public ThreadArmas(Juego partida, Arma armaAsignada, JLabel lblArma,JPanel pnlVida,JLabel vidaArma,ArrayList<Zombie> zombies) {
        this.partida = partida;
        this.armaAsignada = armaAsignada;
        this.lblArma = lblArma;
        this.pnlVida = pnlVida;
        this.vidaArma = vidaArma;
        this.zombies = zombies;
    }

    public void run(){

        while((sigueVivo) && (vigilando)){
            try {
                if(zombies.size() == 0){
                    partida.gano();
                }
                zombieAtacar = armaAsignada.vigilando(zombies);
                if(zombieAtacar != null){
                    vigilando = false;
                    atacando = true;
                }
                vidaArma.setText(String.valueOf(armaAsignada.getVida()));
                pnlVida.setLocation((int)armaAsignada.getPosicionX()  - 5,(int)armaAsignada.getPosicionY() - 30);
                if(armaAsignada.getVida() <= 0 ){
                    partida.eliminarComponente(lblArma);
                    partida.eliminarComponente(pnlVida);
                    sigueVivo = false;
                    return;
                }
                sleep(500);

            }catch (InterruptedException ex){
                System.out.println(ex);
            }

        }
        while ((atacando)&&(!vigilando)){
            try {
                if(zombies.size() == 0){
                    partida.gano();
                }
                if(zombieAtacar.getVida() <= 0){
                    atacando = false;
                    vigilando = true;
                    zombies.remove(zombieAtacar);
                    zombieAtacar = null;
                    this.run();
                }else{
                    if(armaAsignada.getTipo().equals(DEFENSA.MINA)){
                        armaAsignada.atacar(zombies);
                    }else if((armaAsignada.getTipo().equals(DEFENSA.AK47)) || (armaAsignada.getTipo().equals(DEFENSA.TORRE_AEREA))){
                        armaAsignada.atacar(partida,zombieAtacar);
                    } else
                    {
                        armaAsignada.atacar(zombieAtacar);
                    }

                    vidaArma.setText(String.valueOf(armaAsignada.getVida()));
                    pnlVida.setLocation((int)armaAsignada.getPosicionX() - 5,(int)armaAsignada.getPosicionY() - 30);
                    if(armaAsignada.getVida() <= 0 ){
                        partida.eliminarComponente(lblArma);
                        partida.eliminarComponente(pnlVida);
                        sigueVivo = false;
                        return;
                    }
                }
                sleep(1000);
            }catch (InterruptedException ex){
                System.out.println(ex);
            }
        }

    }

    public void setSigueVivo(boolean sigueVivo) {
        this.sigueVivo = sigueVivo;
    }
}
