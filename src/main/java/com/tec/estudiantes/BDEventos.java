package com.tec.estudiantes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BDEventos implements Serializable {

    ArrayList<Evento> usuarios = new ArrayList<>();

    private final String directorio = System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/";
    private final String FILE_PATH = directorio+"BDEventos.dat";

    public BDEventos() {
        restaurar();
    }

    public void guardar(){
        FileManager.writeObject(this, FILE_PATH);
    }
    public void restaurar(){
        BDEventos bd = (BDEventos)FileManager.readObject(FILE_PATH);
        this.usuarios = bd.usuarios;
    }

    public boolean validateUser(String user, String psw){
        for (Evento usuario : usuarios) {
            if ((usuario.getUser().equals(user))  && (usuario.getPassword().equals(psw)))
                return true;
        }
        return false;
    }

    public boolean validateNmbUser(String user){
        for (Evento usuario : usuarios) {
            if (usuario.getUser().equals(user))
                return true;
        }
        return false;
    }
    public Evento crearUsuario(String nombreUsuario,String password){
        boolean existeUsuario = validateNmbUser(nombreUsuario);
        if(existeUsuario)
            return null;
        Evento nuevoUsuario = new Evento(nombreUsuario,password,this);
        usuarios.add(nuevoUsuario);
        guardar();
        return usuarios.get(usuarios.size()-1);
    }

    public Evento getSpecificUser(String user, String psw){
        for (Evento usuario : usuarios) {
            if ((usuario.getUser().equals(user))  && (usuario.getPassword().equals(psw)))
                return usuario;
        }
        return null;
    }



}
