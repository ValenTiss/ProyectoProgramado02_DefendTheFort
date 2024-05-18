/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.tec.estudiantes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Valentin
 */
public class Juego extends javax.swing.JFrame implements Serializable,MouseListener {

    /**
     * Creates new form Juego
     */

    private Partida partida;
    private Juego juego = this;
    private static Map<Espacio,JPanel> espaciosFuerte;

    private Map<Zombie,JLabel> zombiesPresentes;

    private Map<Arma,JLabel> armasPresentes;

    private boolean lvlIsActivePnl = false;

    private boolean armamentoIsActivePnl = false;

    private boolean seleccionoArma = false;

    private Arma armaAgregar;

    private Arma nexoArm;

    private static JLabel lblGanar = new JLabel();

    private static ArrayList<ThreadZombie> accionDeLosZombies;
    private static ArrayList<ThreadArmas> accionDeLasArmas;

    private ArrayList<Zombie> ejercitoZombie;
    private ArrayList<Arma> armasDelUsuario;

    private Map<Arma,ArrayList<JTextField>> stadisticsArmas = new HashMap<>();
    private ArrayList<Integer> zombiesXLvl = new ArrayList<>();

    private static JLabel nexo;

    private JPanel pnlNiveles;
    private JPanel pnlArmamento;

    private JPanel panelSaldo;

    private JLabel saldo;
    private int lvl ;
    private static int SIZE = 30;

    private int mouseX;
    private int mouseY;

    private int saldoDisponible = -1;
    private JButton StartBttn = new JButton();
    private JButton ConfigBttn;

    private JButton cambiarNivel;
    private JButton mostrarArmas;

    private JButton partidaPersonalizada;

    public Juego(Partida partida,Map<Zombie,JLabel> zombiesPresentes, Map<Arma,JLabel> armasPresentes,
    boolean lvlIsActivePnl, boolean armamentoIsActivePnl,boolean seleccionoArma, ArrayList<Zombie> ejercitoZombie,
                 ArrayList<Arma> armasDelUsuario, Map<Arma,ArrayList<JTextField>> stadisticsArmas,Integer lvl)
    {
        this.lvl = lvl;
        this.partida = partida;
        this.zombiesPresentes = zombiesPresentes;
        this.armasPresentes = new HashMap<>();
        this.lvlIsActivePnl = lvlIsActivePnl;
        this.armamentoIsActivePnl = armamentoIsActivePnl;
        this.seleccionoArma = seleccionoArma;
        this.ejercitoZombie = ejercitoZombie;
        this.armasDelUsuario = armasDelUsuario;
        this.stadisticsArmas = stadisticsArmas;
        initCmps();
        initComponents();
        generateFuerte(625);
        buttonsComp();
    }

    private void initCmps(){
        espaciosFuerte = new HashMap<>();
        accionDeLasArmas = new ArrayList<>();
        accionDeLosZombies = new ArrayList<>();
        if(zombiesPresentes == null){
            zombiesPresentes = new HashMap<>();
        }
        cambiarNivel = new JButton();
        partidaPersonalizada = new JButton();
        saldo = new JLabel();
        panelSaldo = new JPanel();
    }

    private Espacio getEspacioByNum(int numeroBuscar){
        for(Map.Entry<Espacio, JPanel> entry : espaciosFuerte.entrySet()){
            if(numeroBuscar == entry.getKey().getNumeroEnLista())
                return entry.getKey();
        }
        return null;
    }
    private double DistSquared(Point pt1, Point pt2) {
        double diffX = pt1.x - pt2.x;
        double diffY = pt1.y - pt2.y;
        return (diffX*diffX+diffY*diffY);
    }


    private Espacio getEspacioByXY(int _x , int _y){
        Espacio espacioCercano  = getEspacioByNum(0);

        Point pntBuscar = new Point(_x,_y);
        double shortestDistance = DistSquared(pntBuscar,new Point(espacioCercano.getPosicionX(),espacioCercano.getPosicionY()));
        Point pntArrayList = new Point();
        for(Map.Entry<Espacio, JPanel> entry : espaciosFuerte.entrySet()){
            pntArrayList.setLocation(entry.getKey().getPosicionX()+(entry.getValue().getWidth() /2),entry.getKey().getPosicionY()+(entry.getValue().getWidth() /2));
            double d = DistSquared(pntBuscar,pntArrayList);
            if (d < shortestDistance) {
                espacioCercano = entry.getKey();
                shortestDistance = d;
            }
        }
        return espacioCercano;
    }
    public static ImageIcon scaleImage(ImageIcon icon, int w, int h) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        Color verde = new Color(50,100,0);
        pnlPrincipal.setBackground(verde);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(2000, 2000, Short.MAX_VALUE)
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(1300, 1300, Short.MAX_VALUE)
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
    private void generateFuerte(int cantidadDeEspacio){
        initBttns();
        int posX = pnlPrincipal.getWidth() / 2 - (SIZE * 12);
        int posY = pnlPrincipal.getHeight() / 2 - (SIZE * 12);
        Color gris = new Color(180,180,180);
        for (int i = 0; i < cantidadDeEspacio; i++) {
            JPanel txf = new JPanel();
            txf.setBackground(gris);
            txf.setBorder(BorderFactory.createLineBorder(Color.black));
            pnlPrincipal.add(txf);

            if(i%25 == 0){
                posX = pnlPrincipal.getWidth() / 80;

            }


            txf.setSize(SIZE, SIZE);

            txf.setLocation(posX+(SIZE*posX++), posY+(i/25*SIZE));

            espaciosFuerte.put(new Espacio(txf.getX(),txf.getY(),i),txf);
        }
        Rectangle perimetroDelFuerte = new Rectangle(getEspacioByNum(0).getPosicionX(),getEspacioByNum(0).getPosicionY()
                                                ,25*SIZE,25*SIZE);

        JPanel perimetroFuerte = new JPanel();
        perimetroFuerte.setSize(25*SIZE,25*SIZE);
        perimetroFuerte.setLocation((int)perimetroDelFuerte.getX(),(int)perimetroDelFuerte.getY());
        perimetroFuerte.addMouseListener(this);
        perimetroFuerte.setOpaque(false);

        Espacio nexoEspacio = getEspacioByXY((int) perimetroDelFuerte.getCenterX(), (int) perimetroDelFuerte.getCenterY());
        nexoArm = ArmasFactory.getNewArma(DEFENSA.NEXUS,nexoEspacio.getPosicionX(),nexoEspacio.getPosicionY());
        nexoEspacio.setArmaAsignada(nexoArm);
        nexo = new JLabel(scaleImage(nexoEspacio.getArmaAsignada().getImagenArma(),SIZE,SIZE));
        JPanel nexoP = espaciosFuerte.get(getEspacioByXY((int)perimetroDelFuerte.getCenterX(),(int)perimetroDelFuerte.getCenterY()));
        nexoP.add(nexo);
        pnlPrincipal.add(perimetroFuerte);
        pnlPrincipal.add(nexoP);
        guardarDatosPartida();
    }

    private void guardarDatosPartida(){
        partida.guardarDatos(lvlIsActivePnl,armamentoIsActivePnl,seleccionoArma,
                ejercitoZombie,armasDelUsuario, stadisticsArmas,lvl);
    }
    private void pnlArmas(boolean activo){
        if(activo){
            pnlArmamento.setVisible(false);
            armamentoIsActivePnl = false;
        }else{
            pnlArmamento.setVisible(true);
            armamentoIsActivePnl = true;
        }
    }

    private void removeAllZombies(){
        for(Map.Entry<Zombie, JLabel> entry
                : zombiesPresentes.entrySet()) {
            pnlPrincipal.remove(entry.getValue());
            pnlPrincipal.revalidate();
            pnlPrincipal.repaint();
        }
        zombiesPresentes.clear();
        accionDeLosZombies.clear();
    }
    private void pnlNiveles(boolean activo){
        if(activo){
            pnlNiveles.setVisible(false);
            lvlIsActivePnl = false;
        }else{
            pnlNiveles.setVisible(true);
            for (int i = 1; i <= 10; i++) {
                JButton nivel =  new JButton("Nivel "+ i);
                int finalI = i;
                nivel.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (ThreadZombie thread:
                             accionDeLosZombies) {
                            thread.setSigueVivo(false);
                        }
                        removeAllZombies();
                        lvl = finalI;
                        zombiesXLvl = Batalla.devolverCantidadZombie(finalI);
                        cargarSaldo();
                        guardarDatosPartida();
                    }
                });
                nivel.setSize(20,50);
                nivel.setLocation(pnlNiveles.getWidth() / 2 - 20 ,20 + (20*i));
                pnlNiveles.add(nivel);
                lvlIsActivePnl = true;
            }
        }
    }

    private void displayArmas(){
        JLabel imgMoneda = new JLabel(scaleImage(new ImageIcon(System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/"+"moneda.png"),5,5));
        ArrayList<Arma> armasDisplay = new ArrayList<>();
        Arma displayAK47 = ArmasFactory.getNewArma(DEFENSA.AK47,0,0);
        Arma displayMalla = ArmasFactory.getNewArma(DEFENSA.MALLA,0,0);
        Arma displayMina = ArmasFactory.getNewArma(DEFENSA.MINA,0,0);
        Arma displayTorreAerea = ArmasFactory.getNewArma(DEFENSA.TORRE_AEREA,0,0);
        armasDisplay.add(displayAK47);
        armasDisplay.add(displayMalla);
        armasDisplay.add(displayMina);
        armasDisplay.add(displayTorreAerea);
        pnlArmamento.setLayout(new GroupLayout(pnlArmamento));
        int i = 1;
        for (Arma armaObj:
             armasDisplay) {
            JLabel displayArma = new JLabel(scaleImage(armaObj.getImagenArma(),150,150));
            displayArma.setSize(150,150);
            displayArma.setLocation((10*i)+(i*150) - 150,pnlArmamento.getHeight() /2 - (displayArma.getHeight()/2) - 20);

            JButton bttnAgregarArma = new JButton("$ "+armaObj.getCosto());
            bttnAgregarArma.setSize(80,30);
            bttnAgregarArma.setLocation(((10*i)+(i*150) - 150) + bttnAgregarArma.getWidth() / 2 ,pnlArmamento.getHeight() /2 - (displayArma.getHeight()/2) + 140);

            bttnAgregarArma.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    seleccionoArma = true;
                    try {
                        armaAgregar = (Arma)armaObj.clone();
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            pnlArmamento.add(displayArma);
            pnlArmamento.add(bttnAgregarArma);
            pnlArmamento.repaint();
            i += 1;
        }
        guardarDatosPartida();
    }

    private Zombie getObjectTypeZombie(Zombie zombieBuscar){
        for (Zombie zombie:
             ejercitoZombie) {
            if(zombie.equals(zombieBuscar)){
                return zombie;
            }
        }
        return null;
    }

    private void repaintArmaDisplay(ArrayList<JTextField> stadistics){
        if(armaAgregar != null){
            stadistics.get(0).setText(String.valueOf(armaAgregar.getVida()));
            stadistics.get(1).setText(String.valueOf(armaAgregar.getDps()));
            stadistics.get(2).setText(String.valueOf(armaAgregar.getRango()/1000));
            pnlArmamento.repaint();
            pnlArmamento.revalidate();
        }
        guardarDatosPartida();
    }
    private void displayArmasPerso(ArrayList<ArrayList<Arma>> armas){
        GroupLayout GL= new GroupLayout(pnlArmamento);
        pnlArmamento.setSize((armas.size()*150)+(armas.size()*200),250);
        pnlArmamento.setLocation((mostrarArmas.getX() - pnlArmamento.getWidth() - 20),mostrarArmas.getY() - 40);
        int posX = 10;
        int i = 1;

        for (ArrayList<Arma> armaXTipo:
                armas) {
            JLabel lblImg = new JLabel(scaleImage(armaXTipo.get(0).getImagenArma(),150,150));
            JLabel vidaArma = new JLabel("Vida:");
            JLabel dpsArma = new JLabel("Daño por segundo:");
            JLabel rangoArma = new JLabel("Rango:");

            lblImg.setSize(150,150);
            lblImg.setLocation(posX++,pnlArmamento.getHeight() / 2 - 70);

            vidaArma.setSize(SIZE,SIZE);
            dpsArma.setSize(SIZE*50,SIZE);
            rangoArma.setSize(SIZE + 10,SIZE);
            vidaArma.setLocation(lblImg.getX() + 170, lblImg.getY() - 30);
            dpsArma.setLocation(vidaArma.getX(),vidaArma.getY()+ (SIZE*2));
            rangoArma.setLocation(dpsArma.getX(),dpsArma.getY()+ (SIZE*2));

            ArrayList<JTextField> stadisticArma = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                JTextField stadistic = new JTextField();
                stadistic.setSize(SIZE+50,25);
                stadistic.setEditable(false);
                switch (j)
                {
                    case 0:
                    {
                        stadistic.setLocation(vidaArma.getX(), vidaArma.getY()+SIZE+5);
                        stadistic.setText(String.valueOf(armaXTipo.get(0).getVida()));

                        break;
                    }
                    case 1:
                    {

                        stadistic.setLocation(dpsArma.getX(), dpsArma.getY()+SIZE+5);
                        stadistic.setText(String.valueOf(armaXTipo.get(0).getDps()));
                       break;
                    }
                    case 2:
                    {
                        stadistic.setLocation(rangoArma.getX(), rangoArma.getY()+SIZE+5);
                        stadistic.setText(String.valueOf(armaXTipo.get(0).getRango()/1000));
                        break;
                    }
                }
                pnlArmamento.add(stadistic);
                stadisticArma.add(stadistic);
            }
            posX = lblImg.getX() + 300;

            JButton bttnAgregar = new JButton("Agregar");
            bttnAgregar.setSize(SIZE+50,SIZE);
            bttnAgregar.setLocation(lblImg.getX() +75 - bttnAgregar.getWidth()/2,lblImg.getY() + lblImg.getHeight() + 20);
            bttnAgregar.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (armaXTipo.get(0) != null) {
                        seleccionoArma = true;
                        armaAgregar = armaXTipo.get(0);
                        armaXTipo.remove(0);
                        repaintArmaDisplay(stadisticArma);

                    }
                    if(armaXTipo.size() == 0 ){
                        for (JTextField txf:
                                stadisticArma) {
                            pnlArmamento.remove(txf);
                        }
                        pnlArmamento.remove(lblImg);
                        pnlArmamento.remove(vidaArma);
                        pnlArmamento.remove(rangoArma);
                        pnlArmamento.remove(dpsArma);
                        pnlArmamento.remove(bttnAgregar);
                        pnlArmamento.repaint();
                        pnlArmamento.revalidate();
                    }
                }
            });

            pnlArmamento.add(bttnAgregar);
            pnlArmamento.add(vidaArma);
            pnlArmamento.add(dpsArma);
            pnlArmamento.add(rangoArma);
            pnlArmamento.add(lblImg);
            GL.addLayoutComponent("Cmp"+ i,lblImg);
            i += 1;
        }
        guardarDatosPartida();

    }

    public static void gano(){
        for (ThreadArmas arma:
                accionDeLasArmas){
            arma.setSigueVivo(false);
        }

        lblGanar.setText("GANASTE!!!");
        lblGanar.setSize(200,20);
        lblGanar.setLocation(nexo.getX(),nexo.getY() - (14* SIZE));
        lblGanar.setVisible(true);
        pnlPrincipal.add(lblGanar);

    }

    public static void perdio(){
        for (ThreadZombie zombie:
                accionDeLosZombies){
            zombie.setSigueVivo(false);
        }
        lblGanar.setText("PERDISTE :(");
        lblGanar.setSize(200,20);
        lblGanar.setLocation(espaciosFuerte.get(10).getX(),espaciosFuerte.get(0).getY() - 30);
        lblGanar.setVisible(true);
        pnlPrincipal.add(lblGanar);
    }
    public void displayPerso(){

        pnlArmamento.removeAll();

        Arma armaCT = armasDelUsuario.get(0);

        ArrayList<Arma> armasAK47 = new ArrayList<>();
        ArrayList<Arma> armasMalla = new ArrayList<>();
        ArrayList<Arma> armasMina = new ArrayList<>();
        ArrayList<Arma> armasTorre = new ArrayList<>();

        for (Arma arma:
             armasDelUsuario)
        {
            switch (arma.getTipo())
            {
                case AK47:
                {
                    armasAK47.add(arma);
                    break;
                }
                case MALLA:
                {
                    armasMalla.add(arma);
                    break;
                }
                case MINA:
                {
                    armasMina.add(arma);
                    break;
                }
                case TORRE_AEREA:
                {
                    armasTorre.add(arma);
                    break;
                }
            }
        }
        ArrayList<ArrayList<Arma>> armasT = new ArrayList<>();
        armasT.add(armasAK47);
        armasT.add(armasMalla);
        armasT.add(armasMina);
        armasT.add(armasTorre);

        ArrayList<ArrayList<Arma>> armasNoVacias = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if(armasT.get(i).size() != 0)
            {
                armasNoVacias.add(armasT.get(i));
            }
        }
        displayArmasPerso(armasNoVacias);
        guardarDatosPartida();
    }


    private void restarDinero(Arma arma){
        saldoDisponible -= arma.getCosto();
    }
    private void cargarSaldo(){
        panelSaldo.removeAll();
        saldoDisponible = Batalla.cantidadDeDinero(lvl);
        JLabel saldoMostrar = new JLabel();
        Color colorMoney = new Color(150, 75, 0);
        panelSaldo.setBackground(colorMoney);
        JLabel imgMoneda = new JLabel(scaleImage(new ImageIcon(System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/"+"moneda.png"),20,20));
        imgMoneda.setSize(20,20);
        imgMoneda.setLocation(0,0);
        panelSaldo.add(imgMoneda);
        panelSaldo.setSize(SIZE+10,SIZE+10);
        panelSaldo.setLocation(cambiarNivel.getX() - panelSaldo.getWidth()/2, 10);

        this.saldo.setText(String.valueOf(saldoDisponible));
        this.saldo.setSize(SIZE/2,SIZE/2 - 5);
        panelSaldo.add(this.saldo);
        panelSaldo.revalidate();
        panelSaldo.repaint();
        guardarDatosPartida();
    }


    private void initBttns(){
        StartBttn = new JButton("Empezar");
        StartBttn.setLocation(this.getWidth() / 2 ,this.getHeight() - 100 );
        StartBttn.setSize(120,50);

        cambiarNivel = new JButton("NIVELES");
        cambiarNivel.setSize(120,50);
        cambiarNivel.setLocation(pnlPrincipal.getWidth() - 140,60);

        mostrarArmas = new JButton("ARMAMENTO");
        mostrarArmas.setSize(120,50);
        mostrarArmas.setLocation(pnlPrincipal.getWidth() - 260,60);

        pnlNiveles = new JPanel();
        pnlNiveles.setVisible(false);
        pnlNiveles.setSize(100,(11*25)+(15* 5) );
        pnlNiveles.setLocation(cambiarNivel.getX() + 10, cambiarNivel.getY() + cambiarNivel.getHeight() + 20);

        partidaPersonalizada.setText("Personalizada");
        partidaPersonalizada.setSize(15,50);
        partidaPersonalizada.setLocation(pnlNiveles.getWidth() / 2 - 20 ,20 + (20*11));


        pnlNiveles.add(partidaPersonalizada);
        pnlArmamento = new JPanel();
        pnlArmamento.setVisible(false);
        pnlArmamento.setSize(150*4 + (20*4),200);
        pnlArmamento.setLocation(mostrarArmas.getX() - pnlArmamento.getWidth() - 10,mostrarArmas.getY() - 40);
        displayArmas();

        cargarSaldo();

        pnlPrincipal.add(pnlNiveles);
        pnlPrincipal.add(pnlArmamento);
        pnlPrincipal.add(mostrarArmas);
        pnlPrincipal.add(cambiarNivel);
        pnlPrincipal.add(StartBttn);
        pnlPrincipal.add(panelSaldo);
        guardarDatosPartida();
    }

    private void generateArmas(ArrayList<Arma> armasEnCampo){
        for (Arma arma :
             armasEnCampo) {
            JPanel lugar = espaciosFuerte.get(getEspacioByXY( arma.getPosicionX(),arma.getPosicionY()));
            Espacio espacioArma = getEspacioByXY(arma.getPosicionX(),arma.getPosicionY());
            espacioArma.setArmaAsignada(arma);
            JLabel lblArma = new JLabel(scaleImage(arma.getImagenArma(),100,100));

            lblArma.setSize(SIZE,SIZE);
            lugar.add(lblArma);
            lblArma.setLocation(arma.getPosicionX(),arma.getPosicionY());
            armasPresentes.put(arma,lblArma);
        }
    }

    private void agregarNuevaArma(){
        Espacio espacioDeLaNuevaArma = getEspacioByXY(mouseX,mouseY);
        JPanel lugar = espaciosFuerte.get(espacioDeLaNuevaArma);
        espacioDeLaNuevaArma.setArmaAsignada(armaAgregar);
        armaAgregar.setPosicionX(espacioDeLaNuevaArma.getPosicionX());
        armaAgregar.setPosicionY(espacioDeLaNuevaArma.getPosicionY());
        JLabel lblArma = new JLabel(scaleImage(armaAgregar.getImagenArma(),SIZE+ 10,SIZE + 10));
        lblArma.setSize(SIZE+ 10,SIZE+ 10);
        lugar.add(lblArma);
        lblArma.setLocation(armaAgregar.getPosicionX(),armaAgregar.getPosicionY());
        armasPresentes.put(armaAgregar,lblArma);

        armasDelUsuario.add(armaAgregar);

        saldoDisponible -= armaAgregar.getCosto();
        saldo.setText(String.valueOf(saldoDisponible));
        panelSaldo.repaint();
        panelSaldo.revalidate();
        armaAgregar = null;
        guardarDatosPartida();
    }



    private void crearHiloArmas(ArrayList<Arma> armasEnCampo){

        Color colorPnlArma = new Color(255,255,255);

        for (Map.Entry<Arma, JLabel> entry
                : armasPresentes.entrySet()) {
            if(entry.getKey() != null){
                JLabel vidaArmalbl = new JLabel(String.valueOf(entry.getKey().getVida()));
                JLabel corazon = new JLabel(scaleImage(new ImageIcon(System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/"+"corazon.png"),SIZE- 10,SIZE - 10));
                JPanel pnlArma = new JPanel();
                pnlArma.add(corazon);
                pnlArma.add(vidaArmalbl);
                pnlArma.setSize(SIZE + 15,SIZE - 7);
                pnlArma.setBackground(colorPnlArma);
                pnlArma.setLayout(new GroupLayout(pnlArma));

                vidaArmalbl.setSize(SIZE- 5,SIZE - 5);
                vidaArmalbl.setLocation(pnlArma.getWidth() - vidaArmalbl.getWidth(),pnlArma.getHeight()-vidaArmalbl.getHeight());
                corazon.setSize(SIZE- 10,SIZE - 10);
                corazon.setLocation(0,pnlArma.getHeight()/2 - corazon.getHeight()/2);


                pnlPrincipal.add(pnlArma);

                ThreadArmas threadArma = new ThreadArmas(this,entry.getKey(),entry.getValue(),pnlArma,vidaArmalbl,ejercitoZombie);
                threadArma.start();
                accionDeLasArmas.add(threadArma);
                try {
                    threadArma.join(10);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
        JLabel vidaNexo = new JLabel(String.valueOf(nexoArm.getVida()));
        JLabel corazon = new JLabel(scaleImage(new ImageIcon(System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/"+"corazon.png"),SIZE- 10,SIZE - 10));
        JPanel pnlArma = new JPanel();
        pnlArma.add(corazon);
        pnlArma.add(vidaNexo);
        pnlArma.setSize(SIZE + 15,SIZE - 7);
        pnlArma.setBackground(colorPnlArma);
        pnlArma.setLayout(new GroupLayout(pnlArma));

        vidaNexo.setSize(SIZE- 5,SIZE - 5);
        vidaNexo.setLocation(pnlArma.getWidth() - vidaNexo.getWidth(),pnlArma.getHeight()-vidaNexo.getHeight());
        corazon.setSize(SIZE- 10,SIZE - 10);
        corazon.setLocation(0,pnlArma.getHeight()/2 - corazon.getHeight()/2);

        ThreadArmas threadArma = new ThreadArmas(this,nexoArm,nexo,pnlArma,vidaNexo,ejercitoZombie);
        threadArma.start();
        pnlPrincipal.setLocation(nexoArm.getPosicionX(),nexo.getY() - 30);
        pnlPrincipal.add(pnlArma);
        accionDeLasArmas.add(threadArma);
    }
    private void generateZombies(ArrayList<Integer> cantidadDeZombies){
        Random rm = new Random();
        int localizacionX,localizacionY;
        int limX = pnlPrincipal.getWidth() - 200;
        int limY = pnlPrincipal.getHeight() - 200 ;

        ejercitoZombie = createZombie();

        int x = 0;
        for(Zombie zombie:ejercitoZombie){
            String newNameZombie = "Zombie "+ x+1;
            JLabel lblZombie = new JLabel();
            pnlPrincipal.add(lblZombie);
            lblZombie.setSize(100,100);
            localizacionX= rm.nextInt(limX);
            localizacionY = rm.nextInt(limY);
            while((espaciosFuerte.get(getEspacioByNum(0)).getX()-100 <  localizacionX) && (espaciosFuerte.get(getEspacioByNum(espaciosFuerte.size()-1)).getX()+100 >  localizacionX)
                    && (espaciosFuerte.get(getEspacioByNum(0)).getY()-100 <  localizacionY) && (espaciosFuerte.get(getEspacioByNum(espaciosFuerte.size()-1)).getY()+100 >  localizacionY)){
                localizacionX= rm.nextInt(limX);
                localizacionY = rm.nextInt(limY);
            }
            lblZombie.setLocation(localizacionX,localizacionY);
            zombie.setPosicionX(localizacionX);
            zombie.setPosicionY(localizacionY);
            zombie.setNombre("Zombie"+ x);
            lblZombie.setIcon(scaleImage(zombie.getImagenZombie(),75,75));
            lblZombie.setSize(100,100);
            zombiesPresentes.put(zombie,lblZombie);
            x +=1;
        }
        crearHiloZombies(zombiesPresentes);

    }

    private void generateZombiesPerso(){
        Random rm = new Random();
        int localizacionX,localizacionY;
        int limX = pnlPrincipal.getWidth() - 200;
        int limY = pnlPrincipal.getHeight() - 200 ;

        int x = 0;
        for(Zombie zombie:ejercitoZombie){
            String newNameZombie = "Zombie "+ x+1;
            JLabel lblZombie = new JLabel();
            pnlPrincipal.add(lblZombie);
            lblZombie.setSize(100,100);
            localizacionX= rm.nextInt(limX);
            localizacionY = rm.nextInt(limY);
            while((espaciosFuerte.get(getEspacioByNum(0)).getX()-100 <  localizacionX) && (espaciosFuerte.get(getEspacioByNum(espaciosFuerte.size()-1)).getX()+100 >  localizacionX)
                    && (espaciosFuerte.get(getEspacioByNum(0)).getY()-100 <  localizacionY) && (espaciosFuerte.get(getEspacioByNum(espaciosFuerte.size()-1)).getY()+100 >  localizacionY)){
                localizacionX= rm.nextInt(limX);
                localizacionY = rm.nextInt(limY);
            }
            lblZombie.setLocation(localizacionX,localizacionY);
            zombie.setPosicionX(localizacionX);
            zombie.setPosicionY(localizacionY);
            zombie.setNombre("Zombie"+ x);
            lblZombie.setIcon(scaleImage(zombie.getImagenZombie(),75,75));
            lblZombie.setSize(100,100);
            zombiesPresentes.put(zombie,lblZombie);
            x +=1;
        }
        crearHiloZombies(zombiesPresentes);
        guardarDatosPartida();
    }

    private ArrayList<Zombie> createZombie(){
        ArrayList<Zombie> listaDeZombie = new ArrayList<>();
        ArrayList<Integer> cantidadZombies  = Batalla.devolverCantidadZombie(lvl);
        int i = 0;
        for (Integer cantidadCrear:
             cantidadZombies) {
            switch (i)
            {
                case 0:
                {
                    for (int j = 0; j < cantidadCrear; j++) {
                        Zombie newZombie = ZombieFactory.getNewZombie(ENEMIGO.NORMAL, "ZombieDC" + i, 0, 0);
                        listaDeZombie.add(newZombie);
                    }
                    break;
                }
                case 1:
                {
                    for (int j = 0; j < cantidadCrear; j++) {
                        Zombie newZombie = ZombieFactory.getNewZombie(ENEMIGO.AEREO,"ZombieAereo"+ i ,0,0);
                        listaDeZombie.add(newZombie);
                    }
                    break;
                }
                case 2:
                {
                    for (int j = 0; j < cantidadCrear; j++) {
                        Zombie newZombie = ZombieFactory.getNewZombie(ENEMIGO.MEDIO_ALCANCE,"ZombieMA"+ i ,0,0);
                        listaDeZombie.add(newZombie);
                    }
                    break;
                }
                case 3:
                {
                    for (int j = 0; j < cantidadCrear; j++) {
                        Zombie newZombie = ZombieFactory.getNewZombie(ENEMIGO.CHOQUE,"ZombieChoque"+ i ,0,0);
                        listaDeZombie.add(newZombie);
                    }
                    break;
                }
            }
            i += 1;
        }
        return listaDeZombie;
    }


    public void crearHiloZombies(Map<Zombie,JLabel> zombiesPresentes){
        Color colorPnlZombie = new Color(178,255,255);
        //create 255 Thread using for loop
        for (Map.Entry<Zombie, JLabel> entry : zombiesPresentes.entrySet()){
            // Create Thread class
            JLabel vidaZombie = new JLabel(String.valueOf(entry.getKey().getVida()));
            JLabel corazon = new JLabel(scaleImage(new ImageIcon(System.getProperty("user.dir")+"/ProyectoProgramado02_DefendTheFort/imgs/"+"corazon.png"),SIZE- 10,SIZE - 10));
            JPanel pnlZombie = new JPanel();
            pnlZombie.add(corazon);
            pnlZombie.add(vidaZombie);
            pnlZombie.setSize(SIZE + 15,SIZE - 7);
            pnlZombie.setBackground(colorPnlZombie);
            pnlZombie.setLayout(new GroupLayout(pnlZombie));

            vidaZombie.setSize(SIZE- 5,SIZE - 5);
            vidaZombie.setLocation(pnlZombie.getWidth() - vidaZombie.getWidth(),pnlZombie.getHeight()-vidaZombie.getHeight());
            corazon.setSize(SIZE- 10,SIZE - 10);
            corazon.setLocation(0,pnlZombie.getHeight()/2 - corazon.getHeight()/2);

            pnlPrincipal.add(pnlZombie);

            ThreadZombie temp = new ThreadZombie(this,entry.getKey(),entry.getValue(),armasDelUsuario,pnlZombie,vidaZombie,nexoArm,nexo);
            temp.start();
            accionDeLosZombies.add(temp);
            try {
                temp.join(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void buttonsComp(){
        StartBttn.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ejercitoZombie.size() < 0){
                    for (Zombie zombie:
                            ejercitoZombie) {
                        ejercitoZombie.remove(zombie);
                    }
                }
                for (Map.Entry<Espacio,JPanel> entry
                        : espaciosFuerte.entrySet()) {
                    if(entry.getKey().getArmaAsignada() == null){
                        entry.getValue().setOpaque(false);
                    }
                }
                pnlPrincipal.repaint();
                pnlPrincipal.revalidate();
                if (lvl != -1){
                    generateZombies(zombiesXLvl);
                }else {
                    generateZombiesPerso();
                }
                crearHiloArmas(armasDelUsuario);
            }
        });

        cambiarNivel.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lblGanar.setVisible(false);
                pnlNiveles(lvlIsActivePnl);
                guardarDatosPartida();
            }
        });

        mostrarArmas.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlArmas(armamentoIsActivePnl);
            }
        });

        partidaPersonalizada.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lvl = -1;
                saldoDisponible = 10000;
                pnlNiveles.revalidate();
                Configuracion config = new Configuracion(juego);
                config.setVisible(true);
                juego.setVisible(false);
                guardarDatosPartida();
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables


    public Map<Zombie, JLabel> getZombiesPresentes() {
        return zombiesPresentes;
    }

    public JLabel getValueByKey(String nombreZombie){
        for(Map.Entry<Zombie, JLabel> entry : zombiesPresentes.entrySet()){
            if (entry.getKey().getNombre().equals(nombreZombie))
                return entry.getValue();
        }
        return null;
    }

    public Zombie getZombieByName(String nombreZombie){
        for(Map.Entry<Zombie, JLabel> entry : zombiesPresentes.entrySet()){
            if (entry.getKey().getNombre().equals(nombreZombie))
                return entry.getKey();
        }
        return null;
    }

    public static void eliminarComponente(JComponent componente){
        pnlPrincipal.remove(componente);
        pnlPrincipal.repaint();
        pnlPrincipal.revalidate();

        for(Map.Entry<Espacio, JPanel> entry
                : espaciosFuerte.entrySet()){
            Component[] components = entry.getValue().getComponents();
            for (Component cmp:
                 components) {
                if(cmp.equals(componente)){
                    entry.getValue().remove(componente);
                    entry.getValue().repaint();
                    entry.getValue().revalidate();
                    entry.getValue().setOpaque(false);
                }
            }
        }
    }

    public static void agregarComponente(JComponent componente){
        pnlPrincipal.add(componente);
        pnlPrincipal.repaint();
        pnlPrincipal.revalidate();
    }

    public ArrayList<Zombie> getEjercitoZombie() {
        return ejercitoZombie;
    }

    public void setEjercitoZombie(ArrayList<Zombie> ejercitoZombie) {
        this.ejercitoZombie = ejercitoZombie;
    }

    public ArrayList<Arma> getArmasDelUsuario() {
        return armasDelUsuario;
    }

    public void setArmasDelUsuario(ArrayList<Arma> armasDelUsuario) {
        this.armasDelUsuario = armasDelUsuario;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if((seleccionoArma)&&(saldoDisponible >= armaAgregar.getCosto()))
        {
            mouseX = getMousePosition().x;
            mouseY = getMousePosition().y - SIZE;
            agregarNuevaArma();
            seleccionoArma = false;
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
