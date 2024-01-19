package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Iterator;

/**
 * a fő ablak amelyen a játék történik
 */
public class SakkFrame extends JFrame {
    private Sakktabla tabla;
    private JPanel panel1, panel2;
    private JLabel uzenet;
    private JPanel[] panelek = new JPanel[8];
    private JButton[] gombok = new JButton[64];     //a sakkmezők gombjai
    private JButton[] gombok2 = new JButton[4];
    private JButton elore, visszafele;
    private Babu_ikonok ikonok = new Babu_ikonok();
    private boolean feher_lep;
    private boolean van_szoveg = true;
    private String nev1, nev2;
    private int ido1, ido2;
    private JLabel nev_ido;
    private Timer feher_ido, fekete_ido;            //a játékosok ideje
    private boolean robot;
    private Tabla_lista lista;
    private int listaelem =0;                       //annak értéke, hogy hány táblaállásunk van a listában

    /**
     * Elindítja a sakkjátékot
     * @param sakktabla a tábla, amellyel elindítja
     * @param kezdo a kezdő színe
     * @param robot annak értéke, hogy robot ellen játszunk-e
     * @param nev1 az első játékos neve
     * @param nev2 a második játékos neve
     * @param ido1 az első játkos ideje
     * @param ido2 a második játékos ideje
     * @param lista a lista ami a játékállásokat tartalmazza
     * @param visszanez annak az értéke, hogy normális játék_e vagy visszanézés
     */
    public SakkFrame(Sakktabla sakktabla, boolean kezdo, boolean robot, String nev1, String nev2, int ido1, int ido2, Tabla_lista lista, boolean visszanez){
        this.ido1=ido1;
        this.ido2=ido2;
        this.nev1=nev1;
        this.nev2=nev2;
        this.robot=robot;
        tabla = sakktabla;
        this.lista= lista;
        if(this.lista==null) {
            this.lista = new Tabla_lista(getJatekos_adatok(), null);
            this.lista.add(tabla, getJatekos_adatok());
        }
        for (int i=0; i<64; ++i){
            tabla.getTabla()[i].setMost_lepett(false);
        }
        feher_lep = kezdo;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sakk");
        setSize(800, 850);
        setLocationRelativeTo(null);
        setResizable(true);
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("menu");
        JMenuItem vissza = new JMenuItem("vissza");
        JMenuItem mentes = new JMenuItem("mentes");
        JMenuItem fomenu = new JMenuItem("fomenu");
        fomenu.addActionListener(new SakkFrame.Fomenu());
        vissza.addActionListener(new SakkFrame.Vissza(robot));
        mentes.addActionListener(new SakkFrame.Mentes());
        if(!visszanez) {
            menu.add(vissza);
            menu.add(mentes);
        }
        menu.add(fomenu);
        menubar.add(menu);
        setJMenuBar(menubar);
        setLayout(new GridLayout(10,1));
        panel1 = new JPanel(new FlowLayout());
        if(visszanez){
            elore = new JButton("Elore");
            visszafele = new JButton("Vissza");
            elore.addActionListener(new SakkFrame.Elore());
            elore.setPreferredSize(new Dimension(150, 60));
            visszafele.addActionListener(new SakkFrame.Visszafele());
            visszafele.setPreferredSize(new Dimension(150, 60));
            panel1.add(visszafele);
            panel1.add(elore);
        }
        else {
            uzenet = new JLabel();
            uzenet.setPreferredSize(new Dimension(600, 70));
            uzenet.setHorizontalAlignment(JTextField.CENTER);
            uzenet.setFont(new Font("SansSerif", Font.BOLD, 20));
            panel1.add(uzenet);
            if (feher_lep)
                uzenet.setText("Feher kezd");
            else
                uzenet.setText("Fekete kezd");
        }
        add(panel1);
        for (int i = 0; i<8; ++i){
            panelek[i]=new JPanel(new FlowLayout());
            for (int j = 0; j<8; ++j){
                gombok[i * 8 + j] = new JButton();
                if(!visszanez)
                    gombok[i * 8 + j].addActionListener(new Gomblenyomas(i*8+j));
                gombok[i * 8 + j].setPreferredSize(new Dimension(75, 75));
                if(tabla.getTabla()[i*8+j].getTipus()!=-1){
                    int szin=0;
                    if(tabla.getTabla()[i*8+j].isFeher_e())
                        szin=6;
                    gombok[i * 8 + j].setIcon(ikonok.getIkonok()[tabla.getTabla()[i * 8 + j].getTipus()+szin]);
                }
                panelek[i].add(gombok[i * 8 + j]);
                if((i%2 == 0 && j%2 == 0) || (i%2 == 1 && j%2 == 1))
                    gombok[i * 8 + j].setBackground(Color.white);
                else
                    gombok[i * 8 + j].setBackground(Color.black);
            }
            add(panelek[i]);
        }
        if(ido1!=-1){
            panel2 = new JPanel(new FlowLayout());
            nev_ido = new JLabel(nev1 + " hatralevo ideje:  "+String.valueOf(ido1/60)+":"+ String.valueOf(ido1-(ido1/60)*60)+"       "+nev2 + " hatralevo ideje:  "+String.valueOf(ido2/60)+":"+ String.valueOf(ido2-(ido2/60)*60));
            nev_ido.setFont(new Font("SansSerif", Font.BOLD, 14));
            panel2.add(nev_ido);
            add(panel2);
            feher_ido = new Timer(1000, new setIdo1());
            fekete_ido = new Timer(1000, new setIdo2());
        }
        else if(nev1!="" || nev2!=""){
            panel2 = new JPanel(new FlowLayout());
            nev_ido = new JLabel(nev1 + "      "+ nev2);
            nev_ido.setFont(new Font("SansSerif", Font.BOLD, 14));
            panel2.add(nev_ido);
            add(panel2);
        }
    }

    /**
     * A mező lenyomásával elvégzi a rajta lévő lépést, vagy megmutatja, hogy merre léphetünk, vagy kiírja, hogy az adott bábu nem a miénk
     */
    private class Gomblenyomas implements ActionListener {
        private int gombszam;

        public Gomblenyomas(int gomb){
            gombszam=gomb;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!tabla.getTabla()[gombszam].isLepheto()){
                for (int i=0; i<64; ++i){
                    tabla.getTabla()[i].setLepheto(false);
                    tabla.getTabla()[i].setLepo(false);
                }
                if(tabla.getTabla()[gombszam].getTipus() == -1) {
                    uzenet.setText("A valasztott mezon nincs babu, kerlek valassz ujat!");
                    van_szoveg = true;
                }
                else if(tabla.getTabla()[gombszam].isFeher_e()==feher_lep){
                    tabla.getTabla()[gombszam].merre_lephet(tabla, false);
                    uzenet.setText("");
                    van_szoveg = false;
                }
                else{
                    uzenet.setText("A valasztott babu nem a te szined, kerlek valassz ujat!");
                    van_szoveg = true;
                }
            }
            else{
                int lepo=0;
                for (int j=0; !tabla.getTabla()[j].isLepo(); ++j)
                    ++lepo;
                for (int i=0; i<64; ++i)
                    tabla.getTabla()[i].setMost_lepett(false);
                if(tabla.getTabla()[lepo].getTipus()!=2 || (gombszam>55 || gombszam<8)){
                    for (int i=0; i<64; ++i)
                        tabla.getTabla()[i].setMost_lepheto(false);
                }
                if(tabla.getTabla()[lepo].getTipus()==2 && (gombszam>55 || gombszam<8)){
                    boolean feher = tabla.getTabla()[lepo].isFeher_e();
                    tabla.setMezo(lepo, -1);
                    if(gombszam>55)
                        tabla.setMezo(lepo, 2);
                    else
                        tabla.setMezo(gombszam, 8);
                    tablat_frissit();
                    gyalogot_leptet(gombszam, feher);
                }
                else
                    tabla.getTabla()[lepo].lep(gombszam, tabla);
                if(robot && (tabla.getTabla()[gombszam].getTipus()!=2 || (gombszam>7 && gombszam<56))){
                    if(tabla.jatek_vege(!feher_lep) > 0){
                        new Jatek_vegeFrame(tabla.jatek_vege(!feher_lep), !feher_lep, lista).setVisible(true);
                        dispose();
                    }
                    for (int i=0; i<64; ++i)
                        tabla.getTabla()[i].setMost_lepett(false);
                    tablat_frissit();
                    new Robot().robot_lep(tabla);
                    lista.add(tabla, getJatekos_adatok());
                }
                else if(tabla.getTabla()[gombszam].getTipus()!=2 || (gombszam>7 && gombszam<56))
                    feher_lep = !feher_lep;
                lista.add(tabla, getJatekos_adatok());
                if (tabla.jatek_vege(feher_lep) > 0) {
                    new Jatek_vegeFrame(tabla.jatek_vege(feher_lep), feher_lep, lista).setVisible(true);
                    feher_ido.stop();
                    fekete_ido.stop();
                    dispose();
                }

                for (int i=0; i<64; ++i){
                    tabla.getTabla()[i].setLepheto(false);
                    tabla.getTabla()[i].setLepo(false);
                }
                if (feher_lep && ido1!= -1) {
                    feher_ido.start();
                    fekete_ido.stop();
                }
                else if (ido1!=-1){
                    fekete_ido.start();
                    feher_ido.stop();
                }
            }
            tablat_frissit();
        }
    }

    /**
     * frissíti a tábla kinézetét a jelenlegi állásra
     */
    public void tablat_frissit(){
        int kiraly_mezo = 0;
        for (int i = 0; i<64; ++i) {
            if (tabla.getTabla()[i].getTipus() != -1) {
                int szin = 0;
                if (tabla.getTabla()[i].isFeher_e())
                    szin = 6;
                gombok[i].setIcon(ikonok.getIkonok()[tabla.getTabla()[i].getTipus() + szin]);
            } else
                gombok[i].setIcon(null);
            if (tabla.getTabla()[i].isLepheto())
                gombok[i].setBackground(Color.orange);
            else if (tabla.getTabla()[i].isLepo())
                gombok[i].setBackground(Color.gray);
            else if (tabla.getTabla()[i].isMost_lepett())
                gombok[i].setBackground(Color.lightGray);
            else if ((i % 16 > 7 && i % 2 == 1) || (i % 16 < 7 && i % 2 == 0))
                gombok[i].setBackground(Color.white);
            else
                gombok[i].setBackground(Color.black);
            if (tabla.getTabla()[i].getTipus() == 4 && tabla.getTabla()[i].isFeher_e() == feher_lep)
                kiraly_mezo = i;

            if (tabla.getTabla()[kiraly_mezo].sakkban_van(tabla, kiraly_mezo) && !van_szoveg) {
                uzenet.setText("A kiralyod sakkban van!");
                uzenet.setForeground(Color.red);
            }
            else if (!van_szoveg) {
                uzenet.setText("");
                uzenet.setForeground(Color.black);
            }
        }
    }

    /**
     * Ha a gyalog belép az ellenfél első sorába, akkor kiválaszthatjuk gombokkal, hogy milyen bábut kérünk helyette
     * @param mezo a mező amire lépett
     * @param feher_e a bábu színe
     */
    private void gyalogot_leptet(int mezo, boolean feher_e){
        for(int i=0; i<64; ++i)
            gombok[i].setEnabled(false);
        panel1.remove(uzenet);
        panel1.repaint();
        for (int i = 0; i < 4; ++i) {
            gombok2[i] = new JButton();
            gombok2[i].setPreferredSize(new Dimension(100, 30));
            gombok2[i].addActionListener(new SakkFrame.Gombnyomas(i, mezo, feher_e));
            panel1.add(gombok2[i]);
            switch (i){
                case 0 :
                    gombok2[i].setText("Bastya");
                    break;
                case 1 :
                    gombok2[i].setText("Futo");
                    break;
                case 2 :
                    gombok2[i].setText("Huszar");
                    break;
                case 3 :
                    gombok2[i].setText("Kiralyno");
                    break;
            }
        }
    }

    /**
     * a gyalog belépésénél, ha kiválasztottuk a nekünk megfelelő bábut, akkor azt odarakja a helyére
     */
    private class Gombnyomas implements ActionListener {
        private int gombszam;
        private int mezo;
        private boolean feher_e;

        public Gombnyomas(int gomb, int mezo, boolean feher_e) {
            gombszam = gomb;
            this.mezo=mezo;
            this.feher_e=feher_e;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int szin =0;
            if(feher_e)
                szin+=6;
            if (gombszam<2)
                tabla.setMezo(mezo, gombszam+szin);
            else if (gombszam==2)
                tabla.setMezo(mezo, 3+szin);
            else
                tabla.setMezo(mezo, 5+szin);
            for(int i=0; i<4;++i)
                panel1.remove(gombok2[i]);
            panel1.add(uzenet);
            panel1.repaint();
            tablat_frissit();
            for(int i=0; i<64; ++i)
                gombok[i].setEnabled(true);
            Iterator<int[]> i = lista.getTablak().iterator();
            while (i.hasNext()) {
                i.next();
                if (!i.hasNext()) {
                    i.remove();
                }
            }
            if(robot) {
                lista.add(tabla, getJatekos_adatok());
                if (tabla.jatek_vege(!feher_lep) > 0) {
                    new Jatek_vegeFrame(tabla.jatek_vege(!feher_lep), !feher_lep, lista).setVisible(true);
                    dispose();
                }
                for (int j = 0; j < 64; ++j)
                    tabla.getTabla()[j].setMost_lepett(false);
                tablat_frissit();
                new Robot().robot_lep(tabla);
                lista.add(tabla, getJatekos_adatok());
                for (int k=0; k<64; ++k){
                    tabla.getTabla()[k].setLepheto(false);
                    tabla.getTabla()[k].setLepo(false);
                }
                tablat_frissit();
            }
            else
                feher_lep = !feher_lep;
        }

    }

    /**
     * csökkenti a fehér idejét, ha letelik, akkor véget vet a játéknak
     */
    private class setIdo1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            --ido1;
            if (ido1 == 0) {
                new Jatek_vegeFrame(2, feher_lep, lista).setVisible(true);
                feher_ido.stop();
                fekete_ido.stop();
                dispose();
            }
            nev_ido.setText(nev1 + " hatralevo ideje:  " + String.valueOf(ido1 / 60) + ":" + String.valueOf(ido1 - (ido1 / 60) * 60) + "       " + nev2 + " hatralevo ideje:  " + String.valueOf(ido2 / 60) + ":" + String.valueOf(ido2 - (ido2 / 60) * 60));

        }
    }

    /**
     * csökkenti a fekete idejét, ha letelik, akkor véget vet a játéknak
     */
    private class setIdo2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            --ido2;
            if (ido2 == 0) {
                new Jatek_vegeFrame(2, feher_lep, lista).setVisible(true);
                feher_ido.stop();
                fekete_ido.stop();
                dispose();
            }
            nev_ido.setText(nev1 + " hatralevo ideje:  " + String.valueOf(ido1 / 60) + ":" + String.valueOf(ido1 - (ido1 / 60) * 60) + "       " + nev2 + " hatralevo ideje:  " + String.valueOf(ido2 / 60) + ":" + String.valueOf(ido2 - (ido2 / 60) * 60));
        }
    }

    /**
     * a főmenübe léptet vissza minket
     */
    private class Fomenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MenuFrame().setVisible(true);
            if(ido1!=-1){
                feher_ido.stop();
                fekete_ido.stop();
            }
            dispose();
        }
    }

    /**
     * visszaléptet az előző állásra
     */
    private class Vissza implements ActionListener {

        boolean robot;

        public Vissza(boolean robot){
            this.robot=robot;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int k=0; k<1 || (k<2 && robot); ++k) {
                Iterator<int[]> i = lista.getTablak().iterator();
                boolean van = false;
                i.next();
                if (i.hasNext())
                    van = true;
                if (van) {
                    lista.vissza(tabla);
                    for (int j = 0; j < 64; ++j) {
                        tabla.getTabla()[j].setMost_lepett(false);
                    }
                    feher_lep = !feher_lep;
                    if (feher_lep && ido1 != -1) {
                        feher_ido.start();
                        fekete_ido.stop();
                    } else if (ido1 != -1) {
                        fekete_ido.start();
                        feher_ido.stop();
                    }
                    tablat_frissit();
                }
            }
        }
    }

    /**
     * menti a játékot
     */
    private class Mentes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                FileOutputStream file = new FileOutputStream("mentett.ser");
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(lista);
                out.close();
                file.close();
                uzenet.setText("Tabla elmentve");
            }
            catch(IOException ex)
            {
                uzenet.setText("A tabla mentese sikertelen volt.");
            }
        }
    }

    /**
     * ha visszanéző módban vagyunk, akkor az előző játékállást mutatja
     */
    private class Visszafele implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(listaelem!=0) {
                for (int j = 0; j < 64; ++j)
                    tabla.setMezo(j, lista.getTablak().get(listaelem - 1)[j]);
                --listaelem;
            }
            for (int j=0; j<64; ++j){
                tabla.getTabla()[j].setMost_lepett(false);
            }
            tablat_frissit();
        }
    }

    /**
     * ha visszanéző módban vagyunk, akkor a következő játékállást mutatja
     */
    private class Elore implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Iterator<int[]> i = lista.getTablak().iterator();
            int[] utolso = null;
            while(i.hasNext())
                utolso = i.next();
            if(utolso != lista.getTablak().get(listaelem)) {
                for (int j = 0; j < 64; ++j)
                    tabla.setMezo(j, lista.getTablak().get(listaelem + 1)[j]);
                ++listaelem;
            }
            for (int j=0; j<64; ++j){
                tabla.getTabla()[j].setMost_lepett(false);
            }
            tablat_frissit();
        }
    }

    /**
     * visszaadja a két játékos adatait
     * @return az adatokat adja vissza egy string tömbben
     */
    public String[] getJatekos_adatok(){
        String[] adatok = new String[5];
        adatok[0] = nev1;
        adatok[1] = nev2;
        adatok[2] = String.valueOf(ido1);
        adatok[3] = String.valueOf(ido2);
        adatok[4] = String.valueOf(feher_lep);
        return  adatok;
    }

    public Sakktabla getSakktabla(){
        return tabla;
    }
}


