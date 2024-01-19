package nhf;

public class Gyalog extends Babu {

    public Gyalog (boolean feher, boolean lepett){
        feher_e = feher;
        lepett_e = lepett;
    }

    /**
     *Ez a függvény vizsgálja meg, hogy hova léphet most a kiválasztott gyalog, az en passant lépést is vizsgálva
     * @param tabla  A tábla amelyen a lépésvizsgálat történik
     * @param sajat_is  Alapesetben sajat bábunk helyére nem léphetünk, azonban ha azt vizsgáljuk, hogy az ellenfél király esetleg leütné a bábunkat,
     *                  akkor fontos megnézni, hogy ott sakkban van_e, vagyis ilyenkor azt az esetet is néznünk kell, hogy saját bábunkra is léphetnénk_e
     */
    public void merre_lephet(Sakktabla tabla, boolean sajat_is){
        int mezo = tablan_keres(tabla);
        tabla.getTabla()[mezo].setLepo(true);
        if (feher_e){
            if(mezo-8 > -1 && tabla.getTabla()[mezo-8].getTipus() == -1 && !sajat_is && (sajat_is || !lepes_utan_sakk(tabla,mezo-8)))
                tabla.getTabla()[mezo-8].setLepheto(true);
            if(mezo-7 > -1 && mezo%8 != 7 && (sajat_is || (tabla.getTabla()[mezo-7].getTipus() != -1 && !tabla.getTabla()[mezo-7].isFeher_e()) || tabla.getTabla()[mezo-7].isMost_lepheto()) && (sajat_is || !lepes_utan_sakk(tabla,mezo-7)))
                tabla.getTabla()[mezo-7].setLepheto(true);
            if(mezo-9 > -1 && mezo%8 != 0 &&(sajat_is || (tabla.getTabla()[mezo-9].getTipus() != -1 && !tabla.getTabla()[mezo-9].isFeher_e()) || tabla.getTabla()[mezo-9].isMost_lepheto()) && (sajat_is || !lepes_utan_sakk(tabla,mezo-9)))
                tabla.getTabla()[mezo-9].setLepheto(true);
            if(!lepett_e && tabla.getTabla()[mezo-16].getTipus() == -1 && tabla.getTabla()[mezo-8].getTipus() == -1 && !sajat_is && (sajat_is || !lepes_utan_sakk(tabla,mezo-16)))
                tabla.getTabla()[mezo-16].setLepheto(true);

        }
        else {
            if(mezo+8 < 64 && tabla.getTabla()[mezo+8].getTipus() == -1 && !sajat_is && (sajat_is || !lepes_utan_sakk(tabla,mezo+8)))
                tabla.getTabla()[mezo+8].setLepheto(true);
            if(mezo+7 < 64 && mezo%8 != 0 && (sajat_is || (tabla.getTabla()[mezo+7].getTipus() != -1 && tabla.getTabla()[mezo+7].isFeher_e()) || tabla.getTabla()[mezo+7].isMost_lepheto()) && (sajat_is || !lepes_utan_sakk(tabla,mezo+7)))
                tabla.getTabla()[mezo+7].setLepheto(true);
            if(mezo+9 < 64 && mezo%8 != 7 && (sajat_is || (tabla.getTabla()[mezo+9].getTipus() != -1 && tabla.getTabla()[mezo+9].isFeher_e()) || tabla.getTabla()[mezo+9].isMost_lepheto()) && (sajat_is || !lepes_utan_sakk(tabla,mezo+9)))
                tabla.getTabla()[mezo+9].setLepheto(true);
            if(!lepett_e && tabla.getTabla()[mezo+16].getTipus() == -1 && tabla.getTabla()[mezo+8].getTipus() == -1 && !sajat_is && (sajat_is || !lepes_utan_sakk(tabla,mezo+16)))
                tabla.getTabla()[mezo+16].setLepheto(true);
        }
    }

    /**
     * A gyalog lépése, ami azért különböző, mivel az en passant lépéshez be kell állítani az átlépett mezőn a most_lepheto-t truera
     * @param mezo  Az a mező, amelyre lép
     * @param tabla  A tábla, amelyen lép
     */
    @Override
    public void lep(int mezo, Sakktabla tabla){
        int tipus= 2;
        if(this.isFeher_e())
            tipus+=6;
        if(tabla.getTabla()[mezo].isMost_lepheto() && mezo > this.tablan_keres(tabla))
            tabla.setMezo(mezo-8, -1);
        else if(tabla.getTabla()[mezo].isMost_lepheto())
            tabla.setMezo(mezo+8, -1);
        for (int i=0; i<64; ++i)
            tabla.getTabla()[i].setMost_lepheto(false);
        tabla.setMezo(mezo, tipus);
        if(mezo-this.tablan_keres(tabla)==16)
            tabla.getTabla()[mezo-8].setMost_lepheto(true);
        else if(mezo-this.tablan_keres(tabla)==-16)
            tabla.getTabla()[mezo+8].setMost_lepheto(true);
        tabla.setMezo(this.tablan_keres(tabla), -1);
    }

    /**
     * A bábu típusát adja vissza
     * @return a bábu típusa a 2
     */
    public int getTipus(){
        return 2;
    }
}
