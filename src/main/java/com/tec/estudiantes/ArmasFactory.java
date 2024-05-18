package com.tec.estudiantes;

import java.io.Serializable;

public class ArmasFactory implements Serializable {
    public static Arma getNewArma(DEFENSA tipo, int _x, int _y){
        switch(tipo){
            case MINA:
            {
                return new Mina(_x,_y);
            }
            case NEXUS:
            {
                return new Nexus(_x,_y);
            }
            case MALLA:
            {
                return new Malla(_x, _y);
            }
            case AK47:
            {
                return new AK47(_x,_y);
            }
            case TORRE_AEREA:
            {
                return new TorreAerea(_x,_y);
            }

        }
        return null;
    }
}
