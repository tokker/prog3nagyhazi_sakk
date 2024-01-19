package tests;

import nhf.Sakktabla;
import org.junit.Test;

import static org.junit.Assert.*;

public class KiralyTest {

    @Test
    public void testSakkban_van(){
        int[] tabla_szamok = {-1,4,-1,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1};
        Sakktabla tabla = new Sakktabla(false, tabla_szamok);
        Sakktabla tabla2 = new Sakktabla(true, null);
        assertEquals(true, tabla.getTabla()[1].sakkban_van(tabla, 1));
        assertEquals(false, tabla2.getTabla()[4].sakkban_van(tabla2, 4));
    }
}
