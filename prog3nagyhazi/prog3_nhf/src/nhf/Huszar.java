package nhf;

public class Huszar extends Babu {

    public Huszar (boolean feher){
        feher_e = feher;
    }

    /**
     *Ez a függvény vizsgálja meg, hogy hova léphet most a kiválasztott huszár
     * @param tabla  A tábla amelyen a lépésvizsgálat történik
     * @param sajat_is  Alapesetben sajat bábunk helyére nem léphetünk, azonban ha azt vizsgáljuk, hogy az ellenfél király esetleg leütné a bábunkat,
     *                  akkor fontos megnézni, hogy ott sakkban van_e, vagyis ilyenkor azt az esetet is néznünk kell, hogy saját bábunkra is léphetnénk_e
     */
    public void merre_lephet(Sakktabla tabla, boolean sajat_is){
        int mezo = tablan_keres(tabla);
        tabla.getTabla()[mezo].setLepo(true);
        if((mezo-17 > -1 && (mezo-17)%8!=7 && (sajat_is || tabla.getTabla()[mezo-17].getTipus() == -1 || tabla.getTabla()[mezo-17].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo-17)))
            tabla.getTabla()[mezo-17].setLepheto(true);
        if((mezo-15 > -1 && (mezo-15)%8!=0 && (sajat_is || tabla.getTabla()[mezo-15].getTipus() == -1 || tabla.getTabla()[mezo-15].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo-15)))
            tabla.getTabla()[mezo-15].setLepheto(true);
        if((mezo-10 > -1 && (mezo-10)%8<6 && (sajat_is || tabla.getTabla()[mezo-10].getTipus() == -1 || tabla.getTabla()[mezo-10].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo-10)))
            tabla.getTabla()[mezo-10].setLepheto(true);
        if((mezo-6 > -1 && (mezo-6)%8>1 && (sajat_is || tabla.getTabla()[mezo-6].getTipus() == -1 || tabla.getTabla()[mezo-6].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo-6)))
            tabla.getTabla()[mezo-6].setLepheto(true);
        if((mezo+17 < 64 && (mezo+17)%8!=0 && (sajat_is || tabla.getTabla()[mezo+17].getTipus() == -1 || tabla.getTabla()[mezo+17].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo+17)))
            tabla.getTabla()[mezo+17].setLepheto(true);
        if((mezo+15 < 64 && (mezo+15)%8!=7 && (sajat_is || tabla.getTabla()[mezo+15].getTipus() == -1 || tabla.getTabla()[mezo+15].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo+15)))
            tabla.getTabla()[mezo+15].setLepheto(true);
        if((mezo+10 < 64 && (mezo+10)%8>1 && (sajat_is || tabla.getTabla()[mezo+10].getTipus() == -1 || tabla.getTabla()[mezo+10].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo+10)))
            tabla.getTabla()[mezo+10].setLepheto(true);
        if((mezo+6 < 64 && (mezo+6)%8<6 && (sajat_is || tabla.getTabla()[mezo+6].getTipus() == -1 || tabla.getTabla()[mezo+6].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())) && (sajat_is || !lepes_utan_sakk(tabla,mezo+6)))
            tabla.getTabla()[mezo+6].setLepheto(true);
    }

    /**
     * A bábu típusát adja vissza
     * @return a bábu típusa a 3
     */
    public int getTipus(){
        return 3;
    }
}
