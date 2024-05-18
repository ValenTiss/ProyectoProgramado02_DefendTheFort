package com.tec.estudiantes;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public enum DEFENSA implements Serializable{
    MALLA,
    MINA,
    AK47,
    NEXUS,
    TORRE_AEREA
}
abstract class Arma extends Espacio implements Serializable,Cloneable {
    private Espacio espacio;
    private int dps;
    private int vida;
    private int rango;
    private int costo;

    private DEFENSA tipo;
    private ArrayList<Zombie> objetivos;
    private ImageIcon imagenArma;
    private final String directorio = System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/";

    public Arma(int _x, int _y, int dps, int vida, int rango,int costo,DEFENSA tipo,String filepath) {
        super(_x,_y,-1);
        this.dps = dps;
        this.vida = vida;
        this.rango = (rango * 10000);
        this.costo = costo;
        this.tipo = tipo;
        imagenArma = new ImageIcon(directorio+filepath);
    }

    public double DistSquared(Point pt1, Point pt2) {
        double diffX = pt1.x - pt2.x;
        double diffY = pt1.y - pt2.y;
        return (diffX*diffX+diffY*diffY);
    }
    public void restarVida(Zombie zombieAtacando) {
        vida -= zombieAtacando.getDps();
    }

    public Zombie vigilando(ArrayList<Zombie> zombies){
        Zombie zombieCerca = null;
        Point armaXY = new Point(this.getPosicionX(),this.getPosicionY());

        Point pntBuscar = new Point(this.getPosicionX(),this.getPosicionY());
        double shortestDistance = DistSquared(pntBuscar,new Point(zombies.get(0).getPosicionX(),zombies.get(0).getPosicionY()));

        Point pntArrayList = new Point();
        for(Zombie zombie:
                zombies){
            if(!zombie.getTipo().equals(ENEMIGO.AEREO)){
                pntArrayList.setLocation(zombie.getPosicionX(),zombie.getPosicionY());
                double d = DistSquared(pntBuscar,pntArrayList);
                if (d < shortestDistance) {
                    zombieCerca = zombie;
                    shortestDistance = d;
                }
            }
        }
        if((zombieCerca != null)&&(shortestDistance <= this.getRango()) &&(!zombieCerca.getTipo().equals(ENEMIGO.AEREO))){
            return zombieCerca;
        }
        return null;

    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    public void atacar(Zombie zombie){
        zombie.restarVida(this);
    }
    public void atacar(ArrayList<Zombie> zombies){
        for (Zombie zombie :
                zombies) {
            zombie.restarVida(this);
        }
    }

    public void atacar(Juego partida , Zombie zombie){
    }

    public int getDps() {
        return dps;
    }
    public ImageIcon getImagenArma() {
        return imagenArma;
    }

    public int getVida() {
        return vida;
    }

    public int getRango() {
        return rango;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public DEFENSA getTipo() {
        return tipo;
    }

    public void setTipo(DEFENSA tipo) {
        this.tipo = tipo;
    }


}

interface IAereo{
    void AtacarZombiesAereos(Juego partida, Zombie zombie);

    Zombie buscarTodosLosZombies();
}
interface IExplosivoArma{
    public void detonar(ArrayList<Zombie> zombies);
}


class AK47 extends Arma implements Serializable {
    public AK47(int _x, int _y) {
        super(_x, _y, 15, 100, 5, 25,DEFENSA.AK47,"AK47MedioAlcance.png");
    }

    public void atacar(Juego partida,Zombie zombie){
        zombie.restarVida(this);
        ThreadBala temp = new ThreadBala(partida,zombie,this);
        temp.start();
        try {
            temp.join(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

 class Nexus extends Arma implements Serializable {
    public Nexus(int _x, int _y) {
        super(_x, _y, 0, 100, 0,0,DEFENSA.NEXUS,"nexus.png");
    }
}

class Mina extends Arma implements Serializable,IExplosivoArma{
    public Mina(int _x, int _y) {
        super(_x, _y, 100, 100, 1, 10,DEFENSA.MINA,"mina.png");
    }


    public Zombie vigilando(ArrayList<Zombie> zombies){

        Zombie zombieCerca = null;
        Point armaXY = new Point(this.getPosicionX(),this.getPosicionY());

        Point pntBuscar = new Point(this.getPosicionX(),this.getPosicionY());
        double shortestDistance = DistSquared(pntBuscar,new Point(zombies.get(0).getPosicionX(),zombies.get(0).getPosicionY()));

        Point pntArrayList = new Point();
        for(Zombie zombie:
                zombies){
            if(!zombie.getTipo().equals(ENEMIGO.AEREO)){
                pntArrayList.setLocation(zombie.getPosicionX(),zombie.getPosicionY());
                double d = DistSquared(pntBuscar,pntArrayList);
                if (d < shortestDistance) {
                    zombieCerca = zombie;
                    shortestDistance = d;
                }
            }
        }
        if((zombieCerca != null)&&(shortestDistance <= 5000) &&(!zombieCerca.getTipo().equals(ENEMIGO.AEREO))){
            return zombieCerca;
        }
        return null;
    }

    public void detonar(ArrayList<Zombie> zombies) {
        for (Zombie zombie :
                zombies) {
            zombie.restarVida(this);
        }
        this.setVida(0);
    }

    public void atacar (ArrayList <Zombie> zombies) {
        ArrayList<Zombie> zombiesAfectadas = new ArrayList<>();
        for (Zombie zombie :
                zombies) {
            int armaX = zombie.getPosicionX();
            int armaY = zombie.getPosicionY();
            double distancia = Math.sqrt(Math.pow((Math.abs(this.getPosicionY() - armaY)), 2) + Math.pow((Math.abs(this.getPosicionX() - armaX)), 2));
            if (distancia <= 100)
                zombiesAfectadas.add(zombie);
        }
        detonar(zombiesAfectadas);
    }


}

class Malla extends Arma implements Serializable{
    public Malla(int _x, int _y) {
        super(_x, _y, 0, 100, 0, 5,DEFENSA.MALLA,"red.png");
    }


    public Zombie vigilando(Zombie zombie) {
        return null;
    }

    @Override
    public void atacar(Zombie zombie) {
    }
}

class TorreAerea extends Arma implements Serializable,IAereo{
    public TorreAerea(int _x, int _y) {
        super(_x, _y, 15, 100, 5, 15,DEFENSA.TORRE_AEREA,"torreAerea.png");
    }

    @Override
    public Zombie vigilando(ArrayList<Zombie> zombies){
        Zombie zombieCerca = null;
        Point armaXY = new Point(this.getPosicionX(),this.getPosicionY());

        Point pntBuscar = new Point(this.getPosicionX(),this.getPosicionY());
        double shortestDistance = DistSquared(pntBuscar,new Point(zombies.get(0).getPosicionX(),zombies.get(0).getPosicionY()));

        Point pntArrayList = new Point();
        for(Zombie zombie:
                zombies){
            if(zombie.getTipo().equals(ENEMIGO.AEREO)){
                pntArrayList.setLocation(zombie.getPosicionX(),zombie.getPosicionY());
                double d = DistSquared(pntBuscar,pntArrayList);
                if (d < shortestDistance) {
                    zombieCerca = zombie;
                    shortestDistance = d;
                }
            }
        }
        if((zombieCerca != null)&&(shortestDistance <= this.getRango()) &&(zombieCerca.getTipo().equals(ENEMIGO.AEREO))){
            return zombieCerca;
        }
        return null;
    }

    @Override
    public void atacar(Juego partida,Zombie zombie){
        zombie.restarVida(this);
        AtacarZombiesAereos(partida,zombie);
    }

    @Override
    public void AtacarZombiesAereos(Juego partida, Zombie zombie) {
        ThreadBala temp = new ThreadBala(partida,zombie,this);
        temp.start();
        try {
            temp.join(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Zombie buscarTodosLosZombies() {
        return null;
    }
}

