package nhf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A táblák állásait és a játék osok adatait tárolja szerializáltan
 */
public class Tabla_lista implements Serializable {
    private ArrayList <int[]>tablak;
    private String[] adatok;

    public Tabla_lista(String[] adatok, ArrayList <int[]>tablak){
        adatok = new String[5];
        tablak = new ArrayList<int[]>();
        this.adatok=adatok;
        this.tablak=tablak;
    }

    /**
     * hozzáad egy táblaállást
     * @param tabla a tábla amelynek az állását hozzáadja
     * @param jatekos_adatok a játékosok adatait beállítja az adatokra
     */
    public void add(Sakktabla tabla, String[] jatekos_adatok){
        tablak.add(tabla.tablabol_szamok());
        adatok = jatekos_adatok;
    }

    /**
     * kitörli a legutolsó táblaállást és beállítja a táblát az előző állásra
     * @param tabla a tábla amelyen elvégzi a visszavonást
     */
    public void vissza(Sakktabla tabla){
        Iterator<int[]> i = tablak.iterator();
        int uj = 0;
        while (i.hasNext()) {
            i.next();
            if (!i.hasNext()) {
                i.remove();
                for (int j = 0; j < 64; ++j)
                    tabla.setMezo(j, tablak.get(uj-1)[j]);
            }
            ++uj;
        }
    }

    public ArrayList <int[]> getTablak(){
        return tablak;
    }

    public String[] getAdatok(){
        return adatok;
    }
}


