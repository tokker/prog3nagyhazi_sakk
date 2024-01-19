package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * A játék végekor előugró ablak
 */
public class Jatek_vegeFrame extends JFrame {
    private Tabla_lista lista;      //a játék állásait tároló lista
    private JLabel uzenet;          //az üzenet, amelyet kiír

    /**
     * @param vegzodes  az érték amellyel végződött a játék
     * @param feher_vesztett  ha nem döntetlen akkor az szín aki veszített
     * @param lista  a játékállásokat tároló lista
     */
    public Jatek_vegeFrame(int vegzodes, boolean feher_vesztett, Tabla_lista lista){
        this.lista=lista;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Jatek vege");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,1));
        JPanel panel = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        uzenet= new JLabel();
        uzenet.setPreferredSize(new Dimension(350, 70));
        uzenet.setHorizontalAlignment(JTextField.CENTER);
        uzenet.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(uzenet);
        add(panel);
        JButton ment = new JButton("Jatek mentese");
        JButton uj_jatek = new JButton("Uj jatek");
        ment.addActionListener(new Jatek_vegeFrame.Ment());
        uj_jatek.addActionListener(new Jatek_vegeFrame.Uj_jatek());
        ment.setPreferredSize(new Dimension(150, 70));
        uj_jatek.setPreferredSize(new Dimension(150, 70));
        panel2.add(ment);
        panel2.add(uj_jatek);
        add(panel2);
        if(vegzodes==1){
            uzenet.setText("Vege a jateknak. Dontetlen.");
        }
        else if(feher_vesztett)
            uzenet.setText("Vege a jateknak. Fekete nyert.");
        else
            uzenet.setText("Vege a jateknak. Feher nyert.");
    }

    /**
     * Elvégzi a játékállások mentését
     */
    private class Ment implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                FileOutputStream file = new FileOutputStream("visszanezheto.ser");
                ObjectOutputStream out = new ObjectOutputStream(file);
                out.writeObject(lista);
                out.close();
                file.close();
                uzenet.setText("Jatekmenet elmentve");
            }
            catch(IOException ex)
            {
                uzenet.setText("A jatekmenet mentese sikertelen volt.");
            }
        }
    }

    /**
     * meghívja a főmenüt
     */
    private class Uj_jatek implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MenuFrame().setVisible(true);
            dispose();
        }
    }
}
