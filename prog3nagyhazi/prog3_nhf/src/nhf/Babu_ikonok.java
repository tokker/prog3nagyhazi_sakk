package nhf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A bábuk képeit tároló osztály
 */
public class Babu_ikonok{
    private ImageIcon[] ikonok = new ImageIcon[12];     //a képek halmaza

    public Babu_ikonok(){
        Image kep;
        String szin;
        String babu = "B";
        for (int i = 0; i < 2; ++i){
            if(i==0)
                szin="B";
            else
                szin="W";
            for(int j=0; j < 6; ++j){
                switch (j) {
                    case 0 :
                        babu = "B";
                        break;
                    case 1 :
                        babu = "F";
                        break;
                    case 2 :
                        babu = "GY";
                        break;
                    case 3 :
                        babu = "H";
                        break;
                    case 4 :
                        babu = "K";
                        break;
                    case 5 :
                        babu = "KN";
                        break;
                }
                try {
                    ikonok[i*6+j] = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("figurak/" + szin + babu + ".gif")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                kep = ikonok[i*6+j].getImage().getScaledInstance(40, 40,  Image.SCALE_SMOOTH);
                ikonok[i*6+j] = new ImageIcon(kep);
            }
        }
    }

    public ImageIcon[] getIkonok() {
        return ikonok;
    }
}
