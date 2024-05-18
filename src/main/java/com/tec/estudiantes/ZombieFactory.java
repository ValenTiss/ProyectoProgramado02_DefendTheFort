package com.tec.estudiantes;

import java.io.Serializable;

public class ZombieFactory implements Serializable {
    public static Zombie getNewZombie(ENEMIGO tipo,String nombre, int _x,int _y){
        switch (tipo) {
            case AEREO:
                return new ZombieAereo(nombre,_x,_y);
            case NORMAL:
                return new ZombieDC(nombre,_x,_y);
            case MEDIO_ALCANCE:
                return new ZombieMA(nombre,_x,_y);
            case CHOQUE:
                return new ZombieChoque(nombre,_x,_y);
        }
        return null;
    }
}
