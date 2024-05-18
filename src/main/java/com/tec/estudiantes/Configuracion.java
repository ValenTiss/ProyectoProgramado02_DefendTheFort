package com.tec.estudiantes;

import javax.swing.*;
import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Configuracion extends  javax.swing.JFrame implements Serializable {

    Configuracion config = this;
    Juego partida;
    private Integer indexArmas = 1;
    private Integer indexZombies = 1;
    private ArrayList<Arma> inventarioDeArmas;
    private ArrayList<Zombie> inventarioDeZombies;

    private Map<JLabel,JTextField> estadisticasZombie;
    private Map<JLabel,JTextField> estadisticasArmas;

    private JPanel pnlPrincipal;
    private JPanel panelZombie;
    private JPanel panelArma;

    JTextArea nuevosZombies;
    JTextArea nuevasArmas;

    JButton bttnZombieDC ;
    JButton bttnZombieAereo ;
    JButton bttnZombieMA ;
    JButton bttnZombieChoque ;
    JButton bttnGuardarZombie ;
    JButton bttnUpArmas;
    JButton bttnDownArmas;

    JButton bttnGuardarArma ;

    JButton bttnVolver;

    JLabel imgZombie;
    JLabel lblVida;
    JLabel lblDps;
    JLabel lblRango;
    JLabel nombreZombie ;

    JLabel imgArma;
    JLabel nombreArma ;

    JTextField vidaZombie;
    JTextField dpsZombie;
    JTextField rangoZombie;
    JTextField vidaArma;
    JTextField dpsArma;
    JTextField rangoArma;



    public Configuracion(Juego partida){
        this.partida = partida;
        estadisticasZombie = new HashMap<>();
        estadisticasArmas = new HashMap<>();
        imgZombie = new JLabel();
        vidaZombie = new JTextField();
        dpsZombie = new JTextField();
        rangoZombie = new JTextField();
        nombreZombie = new JLabel();
        lblVida = new JLabel("Vida:");
        lblDps = new JLabel("Daño por segundo:");
        lblRango = new JLabel("Rango: ");
        imgArma = new JLabel();
        nombreArma = new JLabel();
        vidaArma = new JTextField();
        dpsArma = new JTextField();
        rangoArma = new JTextField();
        inventarioDeArmas = new ArrayList<>();
        inventarioDeZombies = new ArrayList<>();
        nuevosZombies = new JTextArea();
        nuevasArmas = new JTextArea();
        initComponents();
        initPantalla();
    }

    public ImageIcon scaleImage(ImageIcon icon, int w, int h) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
            nw = w;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
            nh = h;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }
        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel()
        {
            @Override
            public boolean isOptimizedDrawingEnabled()
            {
                return false;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
                pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(1000, 1000, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
                pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(1000, 1000, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();

    }// </editor-fold>//GEN-END:initComponents

    private void initPanel(JPanel panelInit){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelInit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelInit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }
    private void initPantalla(){
        JPanel pnlIzq = new JPanel();
        JPanel pnlDer = new JPanel();
        panelZombie = new JPanel();
        Zombie zombieC = ZombieFactory.getNewZombie(ENEMIGO.NORMAL,"Modelo",0,0);
        Arma armaC = ArmasFactory.getNewArma(DEFENSA.AK47,0,0);

        panelArma = new JPanel();

        Color musgo = new Color(50,64,0);
        Color zombie = new Color(50,56,0);
        Color lavanda = new Color(194, 194, 214);
        Color gris = new Color(82, 102, 122);
        Color violeta = new Color(82, 90, 122);

        pnlIzq.setBackground(musgo);
        pnlIzq.setSize(pnlPrincipal.getWidth()/2,(pnlPrincipal.getHeight() / 2) + 200);
        pnlIzq.setLocation(0,pnlPrincipal.getHeight() / 2 - 200);

        nuevosZombies.setSize(pnlIzq.getWidth() - 80,pnlIzq.getHeight() - 80);
        nuevosZombies.setLocation(pnlIzq.getX() + 40,pnlIzq.getY() + 40);
        nuevasArmas.setEditable(false);
        pnlPrincipal.add(nuevosZombies);

        pnlDer.setBackground(lavanda);
        pnlDer.setSize(pnlPrincipal.getWidth()/2,(pnlPrincipal.getHeight() / 2) + 200);
        pnlDer.setLocation(pnlPrincipal.getWidth()/2 + 1,pnlPrincipal.getHeight() / 2 - 200);
        nuevasArmas.setSize(pnlDer.getWidth() - 80,pnlDer.getHeight() - 80);
        nuevasArmas.setLocation(pnlDer.getX() + 40,pnlDer.getY() + 40);
        nuevasArmas.setEditable(false);
        pnlPrincipal.add(nuevasArmas);

        panelZombie.setBackground(zombie);
        panelZombie.setSize(pnlPrincipal.getWidth()/2,(pnlPrincipal.getHeight() / 2) - 200);
        panelZombie.setLocation(0,0);
        panelZombie.setBorder(BorderFactory.createLineBorder(Color.black));

        panelArma.setBackground(violeta);
        panelArma.setSize(pnlPrincipal.getWidth()/2,(pnlPrincipal.getHeight() / 2) - 200);
        panelArma.setLocation(pnlPrincipal.getWidth()/2,0);
        panelArma.setBorder(BorderFactory.createLineBorder(Color.black));

        bttnVolver = new JButton("Jugar");
        bttnVolver.setBackground(Color.BLUE);
        bttnVolver.setSize(120,30);
        bttnVolver.setLocation((pnlPrincipal.getWidth() / 2 - bttnVolver.getWidth()/2),pnlPrincipal.getHeight() - 40 );

        pnlPrincipal.add(bttnVolver);
        pnlPrincipal.add(pnlIzq);
        pnlPrincipal.add(pnlDer);
        pnlPrincipal.add(panelArma);

        initStatisticsZombie(zombieC,panelZombie);
        initStatisticsArmas(armaC,panelArma);
        bttnComp();
        pnlPrincipal.add(panelZombie);

    }

    private void initBttnsArmas(JPanel panelArma){
        bttnUpArmas = new JButton("^");
        bttnDownArmas = new JButton("˅");
        bttnUpArmas.setLocation(40,panelArma.getHeight() / 2 - 50);
        bttnDownArmas.setLocation(40,panelArma.getHeight() / 2 + 50);
        bttnUpArmas.setSize(30,60);
        bttnDownArmas.setSize(30,60);

        bttnGuardarArma = new JButton("Guardar");
        bttnGuardarArma.setSize(120,30);
        bttnGuardarArma.setLocation(panelArma.getWidth() / 2 - 80,panelArma.getHeight() - 45);


        panelArma.add(bttnGuardarArma);
        panelArma.add(bttnUpArmas);
        panelArma.add(bttnDownArmas);

    }

    private void initStatisticsArmas( Arma armaMostrar,JPanel panelArma){
        initBttnsArmas(panelArma);

        ArrayList<JLabel> lblEstadisticas = new ArrayList<>();
        ArrayList<JTextField> txfEstadisticas = new ArrayList<>();
        imgArma.setIcon(scaleImage(armaMostrar.getImagenArma(),200,200));
        JLabel lblVidaArma = new JLabel("Vida:");

        JLabel lblDpsArma = new JLabel("Daño por segundo:");

        JLabel lblRangoArma = new JLabel("Rango:");

        lblEstadisticas.add(lblVidaArma);
        lblEstadisticas.add(lblDpsArma);
        lblEstadisticas.add(lblRangoArma);
        txfEstadisticas.add(vidaArma);
        txfEstadisticas.add(dpsArma);
        txfEstadisticas.add(rangoArma);

        int posY = panelArma.getHeight();
        int i = 0;

        panelArma.setLayout(new GroupLayout(panelArma));

        for (JLabel estadistica:
                lblEstadisticas)
        {
            estadistica.setSize(200,200);
            estadistica.setLocation(panelArma.getWidth() - 150,-60 + (i*70));
            txfEstadisticas.get(i).setLocation(panelArma.getWidth() - 150,estadistica.getY() + 130);
            txfEstadisticas.get(i).setSize(120,20);
            switch (i)
            {
                case 0:
                {
                    txfEstadisticas.get(i).setText(Integer.toString(armaMostrar.getVida()));
                    break;
                }

                case 1:
                {
                    txfEstadisticas.get(i).setText(Integer.toString(armaMostrar.getDps()));
                    break;
                }
                default:
                    txfEstadisticas.get(i).setText(Integer.toString(armaMostrar.getRango()/1000));
                    break;
            }
            estadisticasArmas.put(estadistica,txfEstadisticas.get(i));
            panelArma.add(estadistica);
            panelArma.add(txfEstadisticas.get(i));
            i += 1;
        }
        nombreArma.setText("Arma");
        nombreArma.setSize(new Dimension(80,80));
        nombreArma.setLocation(lblVida.getX() - 150 ,-30);
        imgArma.setLocation(nombreArma.getX() - 70 ,vidaArma.getY() - 20);
        imgArma.setSize(200,200);
        panelArma.add(imgArma);
        panelArma.add(nombreArma);
    }

    private void initBttnsZombie(JPanel panelZombie){
        ArrayList<JButton> tiposDeZombie = new ArrayList<>();
        bttnZombieDC = new JButton("Zombie DC");
        bttnZombieAereo = new JButton("Zombie Aereo");
        bttnZombieMA = new JButton("Zombie MA");
        bttnZombieChoque = new JButton("Zombie Choque");
        bttnGuardarZombie = new JButton("Agregar");
        tiposDeZombie.add(bttnZombieDC);
        tiposDeZombie.add(bttnZombieAereo);
        tiposDeZombie.add(bttnZombieMA);
        tiposDeZombie.add(bttnZombieChoque);
        int i = 0;
        for (JButton bttn :
                tiposDeZombie) {
            bttn.setSize(120,30);
            bttn.setLocation(30,panelZombie.getHeight() / 8 + (i*55));
            pnlPrincipal.add(bttn);
            panelZombie.add(bttn);
            i += 1;
        }
        bttnGuardarZombie.setSize(120,30);
        bttnGuardarZombie.setLocation(panelZombie.getWidth() / 2 - 80,panelZombie.getHeight() - 45);
        panelZombie.add(bttnGuardarZombie);
    }

    private void initStatisticsZombie(Zombie zombie, JPanel panelZombie){
        initBttnsZombie(panelZombie);
        ArrayList<JLabel> lblsZombie = new ArrayList<>();
        ArrayList<JTextField> txfZombies= new ArrayList<>();

        lblsZombie.add(lblVida);
        lblsZombie.add(lblDps);
        lblsZombie.add(lblRango);

        txfZombies.add(vidaZombie);
        txfZombies.add(dpsZombie);
        txfZombies.add(rangoZombie);
        imgZombie.setIcon(scaleImage(zombie.getImagenZombie(),200,200));

        panelZombie.setLayout(new GroupLayout(panelZombie));

        int i = 0;
        for (JLabel estadistica:
                    lblsZombie) {
            estadistica.setSize(200,200);
            estadistica.setLocation(panelZombie.getWidth() - 150,-60 + (i*70));
            txfZombies.get(i).setLocation(panelZombie.getWidth() - 150,estadistica.getY() + 130);
            txfZombies.get(i).setSize(130,20);
            switch (i)
            {
                case 0:
                {

                    txfZombies.get(i).setText(Integer.toString(zombie.getVida()));
                    break;
                }

                case 1:
                {
                    txfZombies.get(i).setText(Integer.toString(zombie.getDps()));
                    break;
                }
                default:
                    txfZombies.get(i).setText(Integer.toString(zombie.getRango()));

                    break;
            }
            estadisticasZombie.put(estadistica,txfZombies.get(i));
            panelZombie.add(estadistica);
            panelZombie.add(txfZombies.get(i));
            i += 1;

        }
        nombreZombie.setText(zombie.getTipo().name());
        nombreZombie.setSize(new Dimension(80,80));
        nombreZombie.setLocation(lblVida.getX() - 150 ,-30);
        imgZombie.setLocation(nombreZombie.getX() - 40 ,vidaZombie.getY() - 20);
        imgZombie.setSize(200,200);
        panelZombie.add(imgZombie);
        panelZombie.add(nombreZombie);
    }


    private void bttnComp(){
        bttnZombieDC.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie zombieDC = new ZombieDC("Zombie Mostrar",0,0);
                indexZombies = 1;
                initStatisticsZombie(zombieDC,panelZombie);
            }
        });
        bttnZombieAereo.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie zombieDC = new ZombieAereo("Zombie Mostrar",0,0);
                indexZombies = 2;
                initStatisticsZombie(zombieDC,panelZombie);
            }
        });
        bttnZombieMA.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie zombieDC = new ZombieMA("Zombie Mostrar",0,0);
                indexZombies = 3;
                initStatisticsZombie(zombieDC,panelZombie);
            }
        });
        bttnZombieChoque.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie zombieDC = new ZombieChoque("Zombie Mostrar",0,0);
                indexZombies = 4;
                initStatisticsZombie(zombieDC,panelZombie);
            }
        });

        bttnGuardarZombie.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie zombieAgregar = null;
                switch (indexZombies)
                {
                    case 1:
                    {
                        zombieAgregar = ZombieFactory.getNewZombie(ENEMIGO.NORMAL,"zombieDCPerso",0,0);
                        break;
                    }

                    case 2:
                    {
                        zombieAgregar = ZombieFactory.getNewZombie(ENEMIGO.AEREO,"zombieAereoPerso",0,0);
                        break;
                    }

                    case 3:
                    {
                        zombieAgregar = ZombieFactory.getNewZombie(ENEMIGO.MEDIO_ALCANCE,"zombieMAPerso",0,0);
                        break;
                    }

                    case 4:
                    {
                        zombieAgregar = ZombieFactory.getNewZombie(ENEMIGO.CHOQUE,"zombieChoquePerso",0,0);

                        break;
                    }
                }
                zombieAgregar.setVida(Integer.parseInt(vidaZombie.getText()));
                zombieAgregar.setDps(Integer.parseInt(dpsZombie.getText()));
                zombieAgregar.setRango(Integer.parseInt(rangoZombie.getText()));
                nuevosZombies.setText(nuevosZombies.getText()+"\n"+ zombieAgregar.toString());

                inventarioDeZombies.add(zombieAgregar);
            }
        });

        bttnGuardarArma.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Arma armaAgregar = null;
                switch (indexArmas)
                {
                    case 1:
                    {
                        armaAgregar = ArmasFactory.getNewArma(DEFENSA.AK47,0,0);
                        break;
                    }
                    case 2:
                    {
                        armaAgregar = ArmasFactory.getNewArma(DEFENSA.MALLA,0,0);
                        break;
                    }
                    case 3:
                    {
                        armaAgregar = ArmasFactory.getNewArma(DEFENSA.MINA,0,0);
                        break;
                    }
                    case 4:
                    {
                        armaAgregar = ArmasFactory.getNewArma(DEFENSA.TORRE_AEREA,0,0);
                        break;
                    }
                }
                armaAgregar.setRango(Integer.parseInt(rangoArma.getText())*1000);
                armaAgregar.setVida(Integer.parseInt(vidaArma.getText()));
                armaAgregar.setDps(Integer.parseInt(dpsArma.getText()));
                String armaToStirng = "|ARMA| Tipo:"+armaAgregar.getTipo()+" , vida: "+armaAgregar.getVida()+" , dps: "+armaAgregar.getDps()+",rango: "+ armaAgregar.getRango() / 1000;
                nuevasArmas.setText(nuevasArmas.getText()+"\n"+ armaToStirng);
                inventarioDeArmas.add(armaAgregar);
            }
        });

        bttnUpArmas.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(indexArmas > 0){
                    indexArmas -= 1;
                    switch (indexArmas)
                    {
                        case 1:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.AK47,0,0),panelArma);
                            break;
                        }
                        case 2:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.MALLA,0,0),panelArma);
                            break;
                        }
                        case 3:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.MINA,0,0),panelArma);
                            break;
                        }
                        case 4:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.TORRE_AEREA,0,0),panelArma);
                            break;
                        }
                    }

                }
            }
        });

        bttnDownArmas.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                if((indexArmas >= 0) && (indexArmas <= 4)){
                    indexArmas += 1;
                    switch (indexArmas)
                    {
                        case 2:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.MALLA,0,0),panelArma);
                            break;
                        }
                        case 3:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.MINA,0,0),panelArma);
                            break;
                        }
                        case 4:
                        {
                            initStatisticsArmas(ArmasFactory.getNewArma(DEFENSA.TORRE_AEREA,0,0),panelArma);
                            break;
                        }
                    }
                }
            }
        });

        bttnVolver.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                partida.setVisible(true);
                partida.setEjercitoZombie(inventarioDeZombies);
                partida.setArmasDelUsuario(inventarioDeArmas);
                config.dispose();
                partida.displayPerso();
            }
        });
    }



    public ArrayList<Arma> getInventarioDeArmas() {
        return inventarioDeArmas;
    }

    public void setInventarioDeArmas(ArrayList<Arma> inventarioDeArmas) {
        this.inventarioDeArmas = inventarioDeArmas;
    }

    public ArrayList<Zombie> getInventarioDEZombies() {
        return inventarioDeZombies;
    }

    public void setInventarioDEZombies(ArrayList<Zombie> inventarioDEZombies) {
        this.inventarioDeZombies = inventarioDEZombies;
    }




}