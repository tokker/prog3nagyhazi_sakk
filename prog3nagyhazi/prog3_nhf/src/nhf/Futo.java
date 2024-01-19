package nhf;

public class Futo extends Babu {

    public Futo (boolean feher){
        feher_e = feher;
    }

    /**
     *Ez a függvény vizsgálja meg, hogy hova léphet most a kiválasztott futó
     * @param tabla  A tábla amelyen a lépésvizsgálat történik
     * @param sajat_is  Alapesetben sajat bábunk helyére nem léphetünk, azonban ha azt vizsgáljuk, hogy az ellenfél király esetleg leütné a bábunkat,
     *                  akkor fontos megnézni, hogy ott sakkban van_e, vagyis ilyenkor azt az esetet is néznünk kell, hogy saját bábunkra is léphetnénk_e
     */
    public void merre_lephet(Sakktabla tabla, boolean sajat_is){
        int mezo = tablan_keres(tabla);
        tabla.getTabla()[mezo].setLepo(true);
        for (int k=1; mezo+k*7 < 64 && (mezo+k*7)%8!=7 &&(tabla.getTabla()[mezo+k*7-7].getTipus()==-1 || k==1); ++k){
            if((sajat_is || tabla.getTabla()[mezo+k*7].getTipus()==-1 || tabla.getTabla()[mezo+k*7].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo+k*7)))
                tabla.getTabla()[mezo+k*7].setLepheto(true);
        }
        for (int k=1; mezo+k*9 < 64 && (mezo+k*9)%8!=0 &&(tabla.getTabla()[mezo+k*9-9].getTipus()==-1 || k==1); ++k){
            if((sajat_is || tabla.getTabla()[mezo+k*9].getTipus()==-1 || tabla.getTabla()[mezo+k*9].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo+k*9)))
                tabla.getTabla()[mezo+k*9].setLepheto(true);
        }
        for (int k=1; mezo-k*7 > -1 && (mezo-k*7)%8!=0 &&(tabla.getTabla()[mezo-k*7+7].getTipus()==-1 || k==1); ++k){
            if((sajat_is || tabla.getTabla()[mezo-k*7].getTipus()==-1 || tabla.getTabla()[mezo-k*7].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo-k*7)))
                tabla.getTabla()[mezo-k*7].setLepheto(true);
        }
        for (int k=1; mezo-k*9 > -1 && (mezo-k*9)%8!=7 &&(tabla.getTabla()[mezo-k*9+9].getTipus()==-1 || k==1); ++k){
            if((sajat_is || tabla.getTabla()[mezo-k*9].getTipus()==-1 || tabla.getTabla()[mezo-k*9].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo-k*9)))
                tabla.getTabla()[mezo-k*9].setLepheto(true);
        }
    }

    /**
     * A bábu típusát adja vissza
     * @return a bábu típusa a 1
     */
    public int getTipus(){
        return 1;
    }
}
