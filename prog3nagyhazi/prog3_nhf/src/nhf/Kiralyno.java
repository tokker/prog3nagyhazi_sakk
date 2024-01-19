package nhf;

public class Kiralyno extends Babu {

    public Kiralyno (boolean feher){
        feher_e = feher;
    }

    /**
     *Ez a függvény vizsgálja meg, hogy hova léphet most a kiválasztott királynő
     * @param tabla  A tábla amelyen a lépésvizsgálat történik
     * @param sajat_is  Alapesetben sajat bábunk helyére nem léphetünk, azonban ha azt vizsgáljuk, hogy az ellenfél király esetleg leütné a bábunkat,
     *                  akkor fontos megnézni, hogy ott sakkban van_e, vagyis ilyenkor azt az esetet is néznünk kell, hogy saját bábunkra is léphetnénk_e
     */
    public void merre_lephet(Sakktabla tabla, boolean sajat_is){
        int mezo = tablan_keres(tabla);
        tabla.getTabla()[mezo].setLepo(true);
        for (int i=1; (i+mezo)%8 !=0 && (tabla.getTabla()[mezo+i-1].getTipus()==-1 || i==1); ++i){
            if((sajat_is || tabla.getTabla()[mezo+i].getTipus()==-1 || tabla.getTabla()[mezo+i].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo+i)))
                tabla.getTabla()[mezo+i].setLepheto(true);
        }
        for (int j=1; (mezo-j)%8 !=7 && mezo-j > -1 && (tabla.getTabla()[mezo-j+1].getTipus()==-1 || j==1); ++j){
            if((sajat_is || tabla.getTabla()[mezo-j].getTipus()==-1 || tabla.getTabla()[mezo-j].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo-j)))
                tabla.getTabla()[mezo-j].setLepheto(true);
        }
        for (int k=1; mezo+k*8 < 64 && (tabla.getTabla()[mezo+k*8-8].getTipus()==-1 || k==1); ++k){
            if((sajat_is || tabla.getTabla()[mezo+k*8].getTipus()==-1 || tabla.getTabla()[mezo+k*8].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo+k*8)))
                tabla.getTabla()[mezo+k*8].setLepheto(true);
        }
        for (int l=1; mezo-l*8 > -1 && (tabla.getTabla()[mezo-l*8+8].getTipus()==-1 || l==1); ++l){
            if((sajat_is || tabla.getTabla()[mezo-l*8].getTipus()==-1 || tabla.getTabla()[mezo-l*8].isFeher_e() == !this.feher_e) && (sajat_is || !lepes_utan_sakk(tabla,mezo-l*8)))
                tabla.getTabla()[mezo-l*8].setLepheto(true);
        }
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
     * @return a bábu típusa a 5
     */
    public int getTipus(){
        return 5;
    }
}
