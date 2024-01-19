package nhf;

/**
 * a bábukat tároló osztály
 */
public abstract class Babu {
    protected boolean feher_e;              //tárolja a bábu színét
    protected boolean lepheto;              //azt jelzi, hogy léphet-e a mezőjére egy kiválasztott bábu
    protected boolean lepo;                 //jelzi, hogy ez_e az éppen lépni készülő bábu
    protected boolean most_lepett = true;   //jelzi, hogy ez a bábu lépett az előző körben, vagy ennek a bábunak a mezőjéről léptek el
    protected boolean most_lepheto;         // a gyalog számára az en passant lépéshez jelzi, hogy ennek a bábunak a mezőjére is léphet ebben a körben
    protected boolean lepett_e;             //azt tárolja, hogy lépett_e már a játékban a megadott bábu

    /**
     *Ez a függvény vizsgálja meg, hogy hova léphet most a kiválasztott bábu
     * @param tabla  A tábla amelyen a lépésvizsgálat történik
     * @param sajat_is  Alapesetben sajat bábunk helyére nem léphetünk, azonban ha azt vizsgáljuk, hogy az ellenfél király esetleg leütné a bábunkat,
     *                  akkor fontos megnézni, hogy ott sakkban van_e, vagyis ilyenkor azt az esetet is néznünk kell, hogy saját bábunkra is léphetnénk_e
     */
    public abstract void merre_lephet(Sakktabla tabla, boolean sajat_is);

    /**
     * Azt vizsgálja, hogy a sakkban van-e a király az adott mezőn, így ezt valójában csak a királyra értelmezzük, minden más bábu esetében false-t ad vissza
     * @param tabla  A tábla amelyen a vizsgálat történik
     * @param mezo  A mező amelyen vizsgáljuk, hogy sakkban van_e a király
     * @return Visszaadja, hogy sakkban van-e ott
     */
    public boolean sakkban_van(Sakktabla tabla, int mezo){
        return false;
    }

    /**
     * Átlép a bábu az egyik mezőről a másikra
     * @param mezo  Az a mező, amelyre lép
     * @param tabla  A tábla, amelyen lép
     */
    public void lep(int mezo, Sakktabla tabla){
        tabla.setMezo(this.tablan_keres(tabla), -1);
        int tipus= 0;
        if(this.getTipus()!= -1 && this.isFeher_e())
            tipus=6;
        tipus += this.getTipus();
        tabla.setMezo(mezo, tipus);
    }

    /**
     * megnézi, hogy ha megteszi a lépést, akkor sakkban van-e a bábuval egyszínű király
     * @param tabla a tábla amelyen lép
     * @param mezo a mező amelyre lép
     * @return visszaadja hogy sakkban lesz_e a király
     */
    public boolean lepes_utan_sakk(Sakktabla tabla, int mezo){
        int[] tabla_szamok;
        tabla_szamok= tabla.tablabol_szamok();
        Sakktabla tabla_masolat = new Sakktabla(false, tabla_szamok);
        tabla_masolat.getTabla()[this.tablan_keres(tabla)].lep(mezo, tabla_masolat);
        int kiraly_mezo = 0;
        for (int i=0; i<64; ++i){
            if(tabla.getTabla()[i].getTipus()==4 && tabla.getTabla()[i].isFeher_e()==this.isFeher_e())
                kiraly_mezo=i;
        }
        if(tabla_masolat.getTabla()[kiraly_mezo].sakkban_van(tabla_masolat, kiraly_mezo))
            return true;
        return false;
    }

    /**
     * Megkeresi, hogy a tábla hanyadik mezőjén van a bábu
     * @param tabla a tábla amelyen keresi
     * @return visszaadja a mezőszámot
     */
    public int tablan_keres(Sakktabla tabla) {
        for (int i = 0; i < 64; ++i) {
            if (tabla.getTabla()[i] == this)
                return i;
        }
        return -1;
    }

    public boolean isFeher_e() {
        return feher_e;
    }

    /**
     * visszaadja számértékkel a bábu típusát
     * @return a típus számértéke
     */
    public abstract int getTipus();

    public boolean isLepheto() {
        return lepheto;
    }

    public void setLepheto(boolean lepheto) {
        this.lepheto = lepheto;
    }

    public boolean isLepo() {
        return lepo;
    }

    public void setLepo(boolean lepo) {
        this.lepo = lepo;
    }

    public boolean isMost_lepheto() {
        return most_lepheto;
    }

    public void setMost_lepheto(boolean most_lepheto) {
        this.most_lepheto = most_lepheto;
    }

    public boolean isMost_lepett() {
        return most_lepett;
    }

    public void setMost_lepett(boolean most_lepett) {
        this.most_lepett = most_lepett;
    }

    public boolean isLepett_e() {
        return lepett_e;
    }
}
