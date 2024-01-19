package tests;

import nhf.Sakktabla;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BabuTest {

    Sakktabla tabla;

    @Before
    public void tablat_letrehoz() {
        int[] tabla_szamok = {-1, -1, -1, 0, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1};
        tabla = new Sakktabla(false, tabla_szamok);
    }

    @Test
    public void testLepes_utan_sakk() {
        boolean sakk = tabla.getTabla()[51].lepes_utan_sakk(tabla, 49);
        boolean sakk2 = tabla.getTabla()[51].lepes_utan_sakk(tabla, 43);
        assertEquals(true, sakk);
        assertEquals(false, sakk2);
    }

    @Test
    public void testMerre_lephet() {
        tabla.getTabla()[59].merre_lephet(tabla, false);
        boolean jo = true;
        for (int i = 0; i < 64; i++) {
            if(i!=58 && i!=60 && i!=50 && i!=52){
                if(tabla.getTabla()[i].isLepheto())
                    jo = false;
            }
            else{
                if(!tabla.getTabla()[i].isLepheto())
                    jo = false;
            }
        }
        assertEquals(true, jo);
    }

    @Test
    public void testLep(){
        tabla.getTabla()[4].lep(12, tabla);
        int[] tabla_szamok2 = {-1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1};
        assertArrayEquals(tabla.tablabol_szamok(), tabla_szamok2);
    }
}
