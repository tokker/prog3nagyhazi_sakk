package nhf;

public class Kiraly extends Babu {

    public Kiraly (boolean feher, boolean lepett){
        feher_e = feher;
        lepett_e = lepett;
    }

    /**
     * Megvizsgállja, hogy sakkban van-e az adott mezőn a király
     * @param tabla  A tábla amelyen a vizsgálat történik
     * @param mezo  A mező amelyen vizsgáljuk, hogy sakkban van_e a király
     * @return visszaadja, hogy sakkban van-e
     */
    @Override
    public boolean sakkban_van(Sakktabla tabla, int mezo){
        int[] tabla_szamok;
        tabla_szamok= tabla.tablabol_szamok();
        Sakktabla tabla_masolat = new Sakktabla(false, tabla_szamok);
        tabla_masolat.setMezo(this.tablan_keres(tabla), -1);
        for(int i=0; i<64; ++i) {
            if(tabla.getTabla()[i].getTipus() != -1 && tabla_masolat.getTabla()[i].isFeher_e()!=this.feher_e)
            tabla_masolat.getTabla()[i].merre_lephet(tabla_masolat, true);
        }
        return tabla_masolat.getTabla()[mezo].isLepheto();
    }

    /**
     *Ez a függvény vizsgálja meg, hogy hova léphet most a kiválasztott király, a sáncot is belevéve, és azt, hogy nem lép_e sakkba
     * @param tabla  A tábla amelyen a lépésvizsgálat történik
     * @param sajat_is  Alapesetben sajat bábunk helyére nem léphetünk, azonban ha azt vizsgáljuk, hogy az ellenfél király esetleg leütné a bábunkat,
     *                  akkor fontos megnézni, hogy ott sakkban van_e, vagyis ilyenkor azt az esetet is néznünk kell, hogy saját bábunkra is léphetnénk_e
     */
    public void merre_lephet(Sakktabla tabla, boolean sajat_is){
        int mezo = tablan_keres(tabla);
        tabla.getTabla()[mezo].setLepo(true);
        if(mezo-8 > -1 && (sajat_is || tabla.getTabla()[mezo-8].getTipus() == -1 || tabla.getTabla()[mezo-8].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo-8)))
            tabla.getTabla()[mezo-8].setLepheto(true);
        if(mezo-7 > -1 && mezo%8 != 7 && (sajat_is || tabla.getTabla()[mezo-7].getTipus() == -1 || tabla.getTabla()[mezo-7].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo-7)))
            tabla.getTabla()[mezo-7].setLepheto(true);
        if(mezo-9 > -1 && mezo%8 != 0 && (sajat_is || tabla.getTabla()[mezo-9].getTipus() == -1 || tabla.getTabla()[mezo-9].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla,mezo-9)))
            tabla.getTabla()[mezo-9].setLepheto(true);
        if(mezo+8 < 64 && (sajat_is || tabla.getTabla()[mezo+8].getTipus() == -1 || tabla.getTabla()[mezo+8].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo+8)))
            tabla.getTabla()[mezo+8].setLepheto(true);
        if(mezo+7 < 64 && (mezo+7)%8 != 7  && (sajat_is || tabla.getTabla()[mezo+7].getTipus() == -1 || tabla.getTabla()[mezo+7].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo+7)))
            tabla.getTabla()[mezo+7].setLepheto(true);
        if(mezo+9 < 64 && (mezo+9)%8 != 0  && (sajat_is || tabla.getTabla()[mezo+9].getTipus() == -1 || tabla.getTabla()[mezo+9].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo+9)))
            tabla.getTabla()[mezo+9].setLepheto(true);
        if((mezo+1)%8 != 0 && (sajat_is || tabla.getTabla()[mezo+1].getTipus() == -1 || tabla.getTabla()[mezo+1].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo+1)))
            tabla.getTabla()[mezo+1].setLepheto(true);
        if(mezo-1 > -1 && (mezo-1)%8 != 7 && (sajat_is || tabla.getTabla()[mezo-1].getTipus() == -1 || tabla.getTabla()[mezo-1].isFeher_e()!=tabla.getTabla()[mezo].isFeher_e())&& (sajat_is || !sakkban_van(tabla, mezo-1)))
            tabla.getTabla()[mezo-1].setLepheto(true);
        if(!this.lepett_e && tabla.getTabla()[mezo-1].getTipus()==-1 && tabla.getTabla()[mezo-2].getTipus()==-1 && tabla.getTabla()[mezo-3].getTipus()==-1 && tabla.getTabla()[mezo-4].getTipus()==0 && !tabla.getTabla()[mezo-4].isLepett_e() && !sakkban_van(tabla, mezo) && !sakkban_van(tabla, mezo-1) && !sakkban_van(tabla, mezo-2))
            tabla.getTabla()[mezo-2].setLepheto(true);
        if(!this.lepett_e && tabla.getTabla()[mezo+1].getTipus()==-1 && tabla.getTabla()[mezo+2].getTipus()==-1 && tabla.getTabla()[mezo+3].getTipus()==0 && !tabla.getTabla()[mezo+3].isLepett_e() && !sakkban_van(tabla, mezo) && !sakkban_van(tabla, mezo+1) && !sakkban_van(tabla, mezo+2))
            tabla.getTabla()[mezo+2].setLepheto(true);
    }

    /**
     * A sánc lépéshez szükséges a felülírás, mivel ekkor a bástyát is léptetjük
     * @param mezo  Az a mező, amelyre lép
     * @param tabla  A tábla, amelyen lép
     */
    @Override
    public void lep(int mezo, Sakktabla tabla){
        int tipus= 4;
        if(this.getTipus()!= -1 && this.isFeher_e())
            tipus+=6;
        tabla.setMezo(mezo, tipus);
        if(mezo-this.tablan_keres(tabla)==2){
            tabla.setMezo(this.tablan_keres(tabla)+3, -1);
            tabla.setMezo(this.tablan_keres(tabla)+1, tipus-4);
        }
        if(mezo-this.tablan_keres(tabla)==-2){
            tabla.setMezo(this.tablan_keres(tabla)-4, -1);
            tabla.setMezo(this.tablan_keres(tabla)-1, tipus-4);
        }
        tabla.setMezo(this.tablan_keres(tabla), -1);
    }

    /**
     * A bábu típusát adja vissza
     * @return a bábu típusa a 4
     */
    public int getTipus(){
        return 4;
    }
}
