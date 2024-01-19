package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A főmenü
 */
public class MenuFrame extends JFrame {

    private JButton vissza, jatek, visszajatszas, tabla_fajlbol, uj_tabla, robot_ellen, jatekos_ellen, indit;
    private JPanel panel1, panel2, panel3;
    private JTextField nev1, nev2;
    private JComboBox ido1, ido2;
    private JLabel nev_1, nev_2, ido_1, ido_2;

    public MenuFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Fomenu");
        setSize(300, 370);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(3,1));
        panel1 = new JPanel(new FlowLayout());
        panel2 = new JPanel(new FlowLayout());
        panel3 = new JPanel(new FlowLayout());
        jatek = new JButton("Jatek");
        jatek.addActionListener(new MenuFrame.Ujjatek());
        visszajatszas = new JButton("Jatek visszajatszasa");
        visszajatszas.addActionListener(new MenuFrame.Visszajatszas());
        jatek.setPreferredSize(new Dimension(200, 80));
        visszajatszas.setPreferredSize(new Dimension(200, 80));
        jatekos_ellen = new JButton("Masik jatekos ellen");
        jatekos_ellen.addActionListener(new MenuFrame.Jatekos_ellen());
        robot_ellen= new JButton("Robot ellen");
        robot_ellen.addActionListener(new MenuFrame.Robot_ellen());
        vissza = new JButton("Vissza");
        vissza.addActionListener(new MenuFrame.Vissza());
        jatekos_ellen.setPreferredSize(new Dimension(200, 80));
        robot_ellen.setPreferredSize(new Dimension(200, 80));
        vissza.setPreferredSize(new Dimension(200, 80));
        tabla_fajlbol = new JButton("Tabla importalasa");
        tabla_fajlbol.addActionListener(new MenuFrame.Tabla_fajlbol());
        uj_tabla = new JButton("Uj tabla letrehozasa");
        uj_tabla.addActionListener(new MenuFrame.Uj_tabla());
        indit = new JButton("Jatek inditasa");
        indit.addActionListener(new MenuFrame.Indit());
        tabla_fajlbol.setPreferredSize(new Dimension(200, 80));
        uj_tabla.setPreferredSize(new Dimension(200, 80));
        indit.setPreferredSize(new Dimension(200, 80));
        nev_1 = new JLabel("Elso jatekos neve:");
        nev_2 = new JLabel("Masodik jatekos neve:");
        ido_1 = new JLabel("Elso jatekos ideje(perc):");
        ido_2 = new JLabel("Masodik jatekos ideje(perc):");
        nev1 = new JTextField(10);
        nev1.setEditable(true);
        nev2 = new JTextField(10);
        nev2.setEditable(true);
        Object[] idok = {1,2,3,5,8,10,20,30};
        ido1 = new JComboBox<>(idok);
        ido2 = new JComboBox<>(idok);
        panel1.add(jatek);
        panel2.add(visszajatszas);
        panel1.add(jatekos_ellen);
        panel2.add(robot_ellen);
        panel3.add(vissza);
        panel1.add(uj_tabla);
        panel2.add(tabla_fajlbol);
        panel2.add(indit);
        panel1.add(nev_1);
        panel1.add(nev1);
        panel1.add(ido_1);
        panel1.add(ido1);
        panel1.add(nev_2);
        panel1.add(nev2);
        panel1.add(ido_2);
        panel1.add(ido2);
        nev_1.setVisible(false);
        nev1.setVisible(false);
        ido_1.setVisible(false);
        ido1.setVisible(false);
        nev_2.setVisible(false);
        nev2.setVisible(false);
        ido_2.setVisible(false);
        ido2.setVisible(false);
        jatekos_ellen.setVisible(false);
        robot_ellen.setVisible(false);
        vissza.setVisible(false);
        uj_tabla.setVisible(false);
        tabla_fajlbol.setVisible(false);
        indit.setVisible(false);
        add(panel1);
        add(panel2);
        add(panel3);
    }

    /**
     * Sakkjáték kezdésének menüpontjába lép
     */
    private class Ujjatek implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jatek.setVisible(false);
            visszajatszas.setVisible(false);
            tabla_fajlbol.setVisible(true);
            uj_tabla.setVisible(true);
            vissza.setVisible(true);
        }
    }

    /**
     * visszalép a menüpontok között
     */
    private class Vissza implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(uj_tabla.isVisible()){
                jatek.setVisible(true);
                visszajatszas.setVisible(true);
                uj_tabla.setVisible(false);
                tabla_fajlbol.setVisible(false);
                vissza.setVisible(false);
            }
            else if(jatekos_ellen.isVisible()){
                uj_tabla.setVisible(true);
                tabla_fajlbol.setVisible(true);
                jatekos_ellen.setVisible(false);
                robot_ellen.setVisible(false);
            }
            else{
                robot_ellen.setVisible(true);
                jatekos_ellen.setVisible(true);
                indit.setVisible(false);
                nev_1.setVisible(false);
                nev1.setVisible(false);
                ido_1.setVisible(false);
                ido1.setVisible(false);
                nev_2.setVisible(false);
                nev2.setVisible(false);
                ido_2.setVisible(false);
                ido2.setVisible(false);
            }
        }
    }

    /**
     * egy másik játékos ellen indítható játék menüpontjának meghívása
     */
    private class Jatekos_ellen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jatekos_ellen.setVisible(false);
            robot_ellen.setVisible(false);
            indit.setVisible(true);
            nev_1.setVisible(true);
            nev1.setVisible(true);
            ido_1.setVisible(true);
            ido1.setVisible(true);
            nev_2.setVisible(true);
            nev2.setVisible(true);
            ido_2.setVisible(true);
            ido2.setVisible(true);
        }
    }

    /**
     * új tábla menüpontjának megnyitása
     */
    private class Uj_tabla implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jatekos_ellen.setVisible(true);
            robot_ellen.setVisible(true);
            uj_tabla.setVisible(false);
            tabla_fajlbol.setVisible(false);
        }
    }

    /**
     * elindítja a játékot egy újonnal létrehozott táblával
     */
    private class Indit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SakkFrame(new Sakktabla(true, null), true, false, nev1.getText(), nev2.getText(), (int)ido1.getSelectedItem()*60, (int)ido2.getSelectedItem()*60, null, false).setVisible(true);
            dispose();
        }
    }

    /**
     * elindít egy robot elleni játékot új táblával
     */
    private class Robot_ellen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SakkFrame(new Sakktabla(true, null), true, true, "", "", -1, -1, null, false).setVisible(true);
            dispose();
        }
    }

    /**
     * elindít egy játékot fájlból
     */
    private class Tabla_fajlbol implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                FileInputStream file = new FileInputStream("mentett.ser");
                ObjectInputStream in = new ObjectInputStream(file);
                Tabla_lista lista;
                lista = (Tabla_lista)in.readObject();
                in.close();
                file.close();
                System.out.println("Sikeres beolvasas");
                String[] jatekos_adatok;
                jatekos_adatok = lista.getAdatok();
                boolean kezdo = Boolean.parseBoolean(jatekos_adatok[4]);
                String nev1 = jatekos_adatok[0];
                String nev2 = jatekos_adatok[1];
                int ido1 = Integer.parseInt(jatekos_adatok[2]);
                int ido2 = Integer.parseInt(jatekos_adatok[3]);
                boolean robot = false;
                if(ido1==-1)
                    robot=true;
                ArrayList<int[]> tablak = lista.getTablak();
                Iterator<int[]> i = tablak.iterator();
                int uj = 0;
                int[] tabla_szamok = null;
                while (i.hasNext()) {
                    i.next();
                    if (!i.hasNext()) {
                        tabla_szamok = lista.getTablak().get(uj);
                    }
                    ++uj;
                }
                new SakkFrame(new Sakktabla(false, tabla_szamok), kezdo, robot,nev1, nev2, ido1, ido2, lista, false).setVisible(true);
            }
            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException is caught");
            }
            dispose();
        }
    }

    /**
     * elindítja egy sakkjátszma visszanézését
     */
    private class Visszajatszas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                FileInputStream file = new FileInputStream("visszanezheto.ser");
                ObjectInputStream in = new ObjectInputStream(file);
                Tabla_lista lista;
                lista = (Tabla_lista)in.readObject();
                in.close();
                file.close();
                System.out.println("Sikeres beolvasas");
                String[] jatekos_adatok;
                jatekos_adatok = lista.getAdatok();
                String nev1 = jatekos_adatok[0];
                String nev2 = jatekos_adatok[1];
                new SakkFrame(new Sakktabla(false, lista.getTablak().get(0)), false, false,nev1, nev2, -1, -1, lista, true).setVisible(true);
            }
            catch(IOException ex)
            {
                System.out.println("IOException is caught");
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException is caught");
            }
            dispose();
        }
    }
}
