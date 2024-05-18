package com.tec.estudiantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class Login extends JFrame implements Serializable {

    private Login lC = this;
    private JPanel panel1;
    private JPanel loginPanel = new JPanel();

    private JTextField textField1;
    private JPasswordField passwordField1 ;
    private JButton loginBttn;
    private JLabel lblUser;
    private JLabel lblPass;
    private JLabel noEntroLbl ;
    private JButton registerButton ;
    BDEventos usuarios;

    Login(String title){
        super(title);
        usuarios = new BDEventos();
        initComponents();
        this.setVisible(true);
        this.setMinimumSize(new Dimension(600,340));
        this.setPreferredSize(new Dimension(600,340));
        this.setMaximumSize(new Dimension(600,340));
        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginBttn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                if (usuarios.validateUser(textField1.getText(), passwordField1.getText())){
                    Partida partida = usuarios.getSpecificUser(textField1.getText(), passwordField1.getText()).getJuegoAsignado();
                    partida.start();
                    lC.dispose();
                    setVisible(false);
                }else
                {
                    noEntroLbl.setText("");
                    noEntroLbl.setText("Usuario o contraseña incorrecta.");
                    passwordField1.setText("");
                }

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evento nuevoUsuario = usuarios.crearUsuario(textField1.getText(), passwordField1.getText());
                if(nuevoUsuario == null){
                    noEntroLbl.setText("");
                    noEntroLbl.setText("Este usuario ya existe.");
                    passwordField1.setText("");
                }else
                {
                    textField1.setText("");
                    passwordField1.setText("");
                    noEntroLbl.setText("Nuevo usuario creado con exito.");
                    usuarios.guardar();
                }
            }
        });
        this.pack();

    }


    private void initComponents(){
        JLabel titulo = new JLabel("Defend The Fortress");
        Color turqoise = new Color(175,238,238);
        loginPanel.setBackground(turqoise);
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        loginBttn = new JButton();
        noEntroLbl = new JLabel();
        registerButton = new JButton();
        lblUser = new JLabel("Username:");
        lblPass = new JLabel("Password:");


        loginPanel.add(lblUser);
        loginPanel.add(lblPass);
        loginPanel.add(titulo);
        loginPanel.add(lblUser);
        loginPanel.add(lblPass);
        loginPanel.add(textField1);
        loginPanel.add(passwordField1);
        loginPanel.add(loginBttn);
        loginPanel.add(noEntroLbl);
        loginPanel.add(registerButton);
        loginPanel.setLayout(new GroupLayout(loginPanel));


        textField1.setSize(200,20);
        textField1.setLocation(210,125);

        passwordField1.setSize(200,20);
        passwordField1.setLocation(textField1.getX(),textField1.getY() + 30);

        lblUser.setSize(200,20);
        lblUser.setLocation(textField1.getX() - 70,textField1.getY());
        lblPass.setSize(200,20);
        lblPass.setLocation(passwordField1.getX() - 70,passwordField1.getY());

        titulo.setFont(new Font("Bombshell",Font.PLAIN,18));
        titulo.setSize(200,20);
        titulo.setLocation(textField1.getX() + textField1.getWidth() / 2 - titulo.getWidth() / 2 + 10,textField1.getY() - 40);

        noEntroLbl.setSize(220,20);
        noEntroLbl.setLocation(passwordField1.getX(),passwordField1.getY() + 30);

        loginBttn.setText("Login");
        loginBttn.setSize(60,20);
        loginBttn.setLocation(passwordField1.getX() + 30,passwordField1.getY() + 60);

        registerButton.setText("Register");
        registerButton.setSize(60,20);
        registerButton.setLocation(loginBttn.getX() + loginBttn.getWidth() + 30,loginBttn.getY());

    }
    public static void main(String[] args) {
        Login jugar = new Login("DefendTheFort");
    }
}

