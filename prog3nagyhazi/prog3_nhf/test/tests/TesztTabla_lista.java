package tests;

import nhf.Sakktabla;
import nhf.Tabla_lista;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TesztTabla_lista {

    Tabla_lista lista;
    int[] vart = {0,3,1,5,4,1,3,0,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,8,8,8,8,8,8,8,8,6,9,7,11,10,7,9,6};

    @Before
    public void menut_indit(){
        lista = new Tabla_lista(null, null);
    }

    @Test
    public void testAdd(){
        Sakktabla tabla = new Sakktabla(true, null);
        String[] stringek = {"da", "af", "","dqf", "da"};
        lista.add(tabla, stringek);
        assertArrayEquals(lista.getAdatok(), stringek);
        assertArrayEquals(lista.getTablak().get(0), vart);
    }

    @Test
    public void testVissza(){
        Sakktabla tabla = new Sakktabla(true, null);
        String[] stringek = {"da", "af", "","dqf", "da"};
        lista.add(tabla, stringek);
        int[] tabla_szamok = {11,4,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1};
        Sakktabla tabla2 = new Sakktabla(false, tabla_szamok);
        lista.add(tabla2, stringek);
        lista.vissza(tabla2);
        assertArrayEquals(lista.getTablak().get(0), vart);
    }
}
