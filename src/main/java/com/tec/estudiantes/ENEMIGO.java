package com.tec.estudiantes;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public enum ENEMIGO implements Serializable{
    NORMAL,
    AEREO,
    CHOQUE,
    MEDIO_ALCANCE
}

abstract class Zombie implements Serializable {
    private int vida;
    private int dps;
    private int velocidadDeMovimiento;
    private int rango;
    private ENEMIGO tipo;
    private int posicionX;
    private int posicionY;
    private String nombre;

    private ImageIcon imagenZombie;

    private final String directorio = System.getProperty("user.dir") + "/ProyectoProgramado02_DefendTheFort/imgs/";

    public Zombie(int vida, int dps, int velocidadDeMovimiento, int rango,ENEMIGO tipo, String nombre, final int _x, final int _y, final String imageZombie) {
        this.vida = vida;
        this.dps = dps;
        this.velocidadDeMovimiento = velocidadDeMovimiento;
        this.rango = (rango * 100);
        this.nombre = nombre;
        this.tipo = tipo;
        posicionX = _x;
        posicionY = _y;
        imagenZombie = new ImageIcon(directorio + imageZombie);
    }

    public void atacar(Arma objetivo){
        objetivo.restarVida(this);
    }

    public void atacar(ArrayList<Arma> objetivos){
        for (Arma objetivo:
                objetivos) {
            objetivo.restarVida(this);
        }
    }



    public void restarVida(Arma armaSeleccionada) {
        vida -= armaSeleccionada.getDps();
    }

    public void mover(int NexoPosicionX, int NexoPosicionY) {
        double angulo;
        int opuesto = Math.abs(NexoPosicionY - this.getPosicionY());
        int adyacente = Math.abs(NexoPosicionX - this.getPosicionX());

        angulo = Math.toDegrees(Math.atan2(opuesto, adyacente));

        if ((this.getPosicionY() < NexoPosicionY) && (this.getPosicionX() < NexoPosicionX)) {
            posicionX -= Math.round(Math.cos(angulo) * velocidadDeMovimiento);
            posicionY -= Math.round(Math.sin(angulo) * velocidadDeMovimiento);
        } else if ((this.getPosicionY() > NexoPosicionY) && (this.getPosicionX() > NexoPosicionX)) {
            posicionX += Math.round(Math.cos(angulo) * velocidadDeMovimiento);
            posicionY += Math.round(Math.sin(angulo) * velocidadDeMovimiento);
        } else if ((this.getPosicionY() > NexoPosicionY)) {
            posicionX += Math.round(Math.cos(angulo) * velocidadDeMovimiento);
            posicionY -= Math.round(Math.sin(angulo) * velocidadDeMovimiento);
        } else {
            posicionX -= Math.round(Math.cos(angulo) * velocidadDeMovimiento);
            posicionY += Math.round(Math.sin(angulo) * velocidadDeMovimiento);
        }

    }

    public Arma vigilando(ArrayList<Arma> armas){
        for (Arma arma:
                armas) {
            int armaX = arma.getPosicionX();
            int armaY = arma.getPosicionY();
            double distancia = Math.sqrt(Math.pow((Math.abs(this.getPosicionY()-armaY)),2)+Math.pow((Math.abs(this.getPosicionX()-armaX)),2));
            if((distancia <= rango * 3)&&(!arma.getTipo().equals(DEFENSA.MINA)))
                return arma;
        }
        return null;
    }

    public int getDps() {
        return dps;
    }


    public ENEMIGO getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getVelocidadDeMovimiento() {
        return velocidadDeMovimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirectorio() {
        return directorio;
    }

    public ImageIcon getImagenZombie() {
        return imagenZombie;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public String toString(){
        return "|ZOMBIE| Tipo:" + this.getTipo() + " , vida: " + this.getVida() + " , dps: " + this.getDps() + ",rango: " + this.getRango();
    }
}

interface IExplosivoZombie{
    void explotar(ArrayList<Arma> armas);
}


 class ZombieDC extends Zombie implements Serializable {
    public ZombieDC(String nombre,int _x ,int _y) {
        super(100, 25, 2, 1,ENEMIGO.NORMAL,nombre,_x,_y,"zombie.png");
    }

}
 class ZombieAereo extends Zombie  implements Serializable{
    public ZombieAereo(String nombre, int _x, int _y) {
        super(75, 15, 1, 1,ENEMIGO.AEREO,nombre, _x, _y,"zombieAereo.png");
    }
}

 class ZombieMA extends Zombie implements Serializable {
    public ZombieMA(String nombre,int _x ,int _y){
        super(200, 15, 3, 4,ENEMIGO.MEDIO_ALCANCE,nombre,_x,_y,"zombieMA.png");
    }

    public void atacar() {
        System.out.println("TODO");
    }
}

 class ZombieChoque extends Zombie implements Serializable,IExplosivoZombie {
     public ZombieChoque(String nombre, int _x, int _y) {
         super(500, 100, 1, 2, ENEMIGO.CHOQUE, nombre, _x, _y, "choque.png");

     }
     public void explotar(ArrayList<Arma> armas) {
         for (Arma arma :
                 armas) {
             arma.restarVida(this);
         }
         this.setVida(0);
     }

     public void atacar (ArrayList <Arma> armas) {
         ArrayList<Arma> armasAfectadas = new ArrayList<>();
         for (Arma arma :
                 armas) {
             int armaX = arma.getPosicionX();
             int armaY = arma.getPosicionY();
             double distancia = Math.sqrt(Math.pow((Math.abs(this.getPosicionY() - armaY)), 2) + Math.pow((Math.abs(this.getPosicionX() - armaX)), 2));
             if (distancia <= 100)
                 armasAfectadas.add(arma);
         }
         explotar(armasAfectadas);
     }
 }

