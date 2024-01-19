package nhf;

public class Sakktabla {
    private Babu[] tabla = new Babu[64];

    public Sakktabla(boolean uj, int[] tabla_szamokkal) {
        if (uj) {
            for (int i = 0; i < 64; ++i) {
                if (i == 0 || i == 7)
                    tabla[i] = new Bastya(false, false);
                else if (i == 1 || i == 6)
                    tabla[i] = new Huszar(false);
                else if (i == 2 || i == 5)
                    tabla[i] = new Futo(false);
                else if (i == 3)
                    tabla[i] = new Kiralyno(false);
                else if (i == 4)
                    tabla[i] = new Kiraly(false, false);
                else if (i < 16)
                    tabla[i] = new Gyalog(false, false);
                else if (i < 48)
                    tabla[i] = new Ures();
                else if (i < 56)
                    tabla[i] = new Gyalog(true, false);
                else if (i == 56 || i == 63)
                    tabla[i] = new Bastya(true, false);
                else if (i == 57 || i == 62)
                    tabla[i] = new Huszar(true);
                else if (i == 58 || i == 61)
                    tabla[i] = new Futo(true);
                else if (i == 59)
                    tabla[i] = new Kiralyno(true);
                else
                    tabla[i] = new Kiraly(true, false);
            }
        } else {
            for (int i = 0; i < 64; ++i) {
                setMezo(i, tabla_szamokkal[i]);
            }
        }
    }

    public Babu[] getTabla() {
        return tabla;
    }

    /**
     * átállít egy mezőt úgy, hogy azon másik bábu legyen
     * @param mezoszam a mező sorszáma
     * @param tipus a bábu típusa, amire állítja a mezőt
     */
    public void setMezo(int mezoszam, int tipus) {
        switch (tipus) {
            case -1 :
                tabla[mezoszam] = new Ures();
                break;
            case 0 :
                tabla[mezoszam] = new Bastya(false, true);
                break;
            case 1 :
                tabla[mezoszam] = new Futo(false);
                break;
            case 2 :
                {
                if (mezoszam <16)
                    tabla[mezoszam] = new Gyalog(false, false);

                else
                    tabla[mezoszam] = new Gyalog(false, true);
                break;

            }

            case 3 :
                tabla[mezoszam] = new Huszar(false);
                break;
            case 4 :
                tabla[mezoszam] = new Kiraly(false, true);
                break;
            case 5 :
                tabla[mezoszam] = new Kiralyno(false);
                break;
            case 6 :
                tabla[mezoszam] = new Bastya(true, true);
                break;
            case 7 :
                tabla[mezoszam] = new Futo(true);
                break;
            case 8 :
                {
                if(mezoszam >47)
                    tabla[mezoszam] = new Gyalog(true, false);
                else
                    tabla[mezoszam] = new Gyalog(true, true);
                break;
            }
            case 9 :
                tabla[mezoszam] = new Huszar(true);
                break;
            case 10 :
                tabla[mezoszam] = new Kiraly(true, true);
                break;
            case 11 :
                tabla[mezoszam] = new Kiralyno(true);
                break;
        }
    }

    /**
     * A táblán található bábukat reprezentálja egy 64 elemű int tömbben
     * @return az int tömb amit csinál a táblából
     */
    public int[] tablabol_szamok() {
        int[] tabla_tomb = new int[64];
        for (int i = 0; i < 64; ++i) {
            tabla_tomb[i] = tabla[i].getTipus();
            if (tabla[i].getTipus() != -1 && tabla[i].isFeher_e())
                tabla_tomb[i] += 6;
        }
        return tabla_tomb;
    }

    /**
     * megvizsgálja, hogy vége van-e a játéknak
     * @param feher_lep a bábu színe ami éppen lép
     * @return visszaadja a játék kimenetelét, ha 0, akkor folytatódik, ha 1, akkor döntetlennel vége, ha 2, akkor valaki mattot adott
     */
    public int jatek_vege(boolean feher_lep) {
        int[] tabla_szamok;
        tabla_szamok = this.tablabol_szamok();
        Sakktabla tabla_masolat = new Sakktabla(false, tabla_szamok);
        for (int i = 0; i < 64; ++i) {
            if (tabla_masolat.getTabla()[i].getTipus() != -1 && tabla_masolat.getTabla()[i].isFeher_e() == feher_lep)
                tabla_masolat.getTabla()[i].merre_lephet(tabla_masolat, false);
        }
        boolean matt = true;
        boolean dontetlen = true;
        int huszarszam=0;
        int feketen_futo = 0;
        int feheren_futo = 0;
        for (int i = 0; i < 64; ++i) {
            if (tabla_masolat.getTabla()[i].isLepheto())
                matt = false;
            if(tabla_masolat.getTabla()[i].getTipus()==0 || tabla_masolat.getTabla()[i].getTipus()==2 || tabla_masolat.getTabla()[i].getTipus()==5)
                dontetlen = false;
            if(tabla_masolat.getTabla()[i].getTipus()==3)
                ++huszarszam;
            if(tabla_masolat.getTabla()[i].getTipus()==1 && ((i%2==1 && i%16 <8)||(i%2==0 && i%16 >7)))
                ++feketen_futo;
            if(tabla_masolat.getTabla()[i].getTipus()==1 && ((i%2==0 && i%16 <8)||(i%2==1 && i%16 >7)))
                ++feheren_futo;
        }
        if(huszarszam>1  || (huszarszam==1 && (feheren_futo >0 || feketen_futo>0)) || (feheren_futo >0 && feketen_futo>0))
            dontetlen = false;

        if (matt || dontetlen) {
            int kiraly_mezo = 0;
            for (int i = 0; i < 64; ++i) {
                if (tabla[i].getTipus() == 4 && tabla[i].isFeher_e() == feher_lep)
                    kiraly_mezo = i;
            }
            if (!dontetlen && tabla_masolat.getTabla()[kiraly_mezo].sakkban_van(tabla_masolat, kiraly_mezo)) {
                return 2;
            }
            return 1;
        }
        return 0;
    }
}
