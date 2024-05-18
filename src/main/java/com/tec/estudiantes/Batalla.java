package com.tec.estudiantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Batalla implements Serializable {
    private Integer lvl;
    private Integer terminoJuego;

    private static final int[][] MatrixcantidadZombiesXLvl = { {20,5,0,0},
                                                        {20,5,5,0},
                                                        {20,5,0,5},
                                                        {20,5,5,5},
                                                        {20,5,10,5},
                                                        {20,5,10,10},
                                                        {20,10,10,10},
                                                        {30,10,10,15},
                                                        {30,15,10,15},
                                                        {20,20,20,20}};

    private static final ArrayList<Integer> cantidadDineroXLvl = new ArrayList<>(Arrays.asList(100,200,300,400,500,600,700,800,900,10000));


    public static ArrayList<Integer> devolverCantidadZombie(int nivel){
        if((nivel > -1)&&(nivel <= 10)){
            ArrayList<Integer> listadevolver = new ArrayList<>();
            for (Integer num:
                 MatrixcantidadZombiesXLvl[nivel-1]) {
                listadevolver.add(num);
            }
            return listadevolver;
        }else{
            return null;
        }

    }

    public static Integer cantidadDeDinero(int nivel){
        if((nivel > -1)&&(nivel <= 10)){
            for (int i = 0; i < 10; i++) {
                if(i == nivel-1)
                    return cantidadDineroXLvl.get(i);
            }
        }else if(nivel == -1){
            return 10000;
        }
        return null;
    }

}
