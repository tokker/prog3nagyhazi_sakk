package nhf;

import java.util.Random;

public class Robot {

    /**
     * elvégzi a gép random lépését
     * @param tabla a tábla amelyen a lépés történik
     */
    public void robot_lep(Sakktabla tabla){
        Random random = new Random();
        int hany_lepo=0;
        int[] seged_szamok = new int[64];
        for(int i=0; i<64; ++i)
            tabla.getTabla()[i].setLepheto(false);
        for(int i=0; i<64; ++i){
            seged_szamok[i]=0;
            if(tabla.getTabla()[i].getTipus()!=-1 && !tabla.getTabla()[i].isFeher_e())
                tabla.getTabla()[i].merre_lephet(tabla, false);
            for(int j=0; j<64; ++j){
                if(tabla.getTabla()[j].isLepheto()){
                    tabla.getTabla()[j].setLepheto(false);
                    if(seged_szamok[i]!=1) {
                        seged_szamok[i] = 1;
                        ++hany_lepo;
                    }
                }
            }
        }
        int lepo=0;
        if(hany_lepo!=0)
            lepo = random.nextInt(hany_lepo)+1;
        int lepo_mezo=0;
        for(int i=0; i<64; ++i){
            if(seged_szamok[i]==1){
                --lepo;
            }
            if(lepo==0) {
                lepo_mezo = i;
                --lepo;
            }
        }
        tabla.getTabla()[lepo_mezo].merre_lephet(tabla, false);
        int[] seged_szamok2 = new int[64];
        int hany_helyre =0;
        for(int i=0; i<64; ++i){
            seged_szamok2[i]=0;
            if(tabla.getTabla()[i].isLepheto()) {
                seged_szamok2[i] = 1;
                tabla.getTabla()[i].setLepheto(false);
                ++hany_helyre;
            }
        }
        int ide = random.nextInt(hany_helyre)+1;
        int hova_lep=0;
        for(int i=0; i<64; ++i){
            if(seged_szamok2[i]==1){
                --ide;
            }
            if(ide==0) {
                hova_lep = i;
                --ide;
            }
        }
        if(tabla.getTabla()[lepo_mezo].getTipus()==2 && (hova_lep>55 || hova_lep<8)){
            int fajta = random.nextInt(4);
            int szin=0;
            if(tabla.getTabla()[lepo_mezo].isFeher_e())
                szin+=6;
            if (fajta<2)
                tabla.setMezo(hova_lep, fajta+szin);
            else if (fajta==2)
                tabla.setMezo(hova_lep, 3+szin);
            else
                tabla.setMezo(hova_lep, 5+szin);
        }
        else
            tabla.getTabla()[lepo_mezo].lep(hova_lep, tabla);
        tabla.setMezo(lepo_mezo, -1);
    }
}
