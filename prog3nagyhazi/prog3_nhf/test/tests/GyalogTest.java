package tests;

import nhf.Sakktabla;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GyalogTest {

    @Test
    public void Kettot_lepTest(){
        Sakktabla tabla = new Sakktabla(true, null);
        tabla.getTabla()[54].lep(38, tabla);
        assertEquals(true, tabla.getTabla()[46].isMost_lepheto());
    }
}
