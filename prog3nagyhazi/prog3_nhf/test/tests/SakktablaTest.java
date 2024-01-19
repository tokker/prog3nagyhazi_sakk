package tests;

import nhf.Sakktabla;
import org.junit.Test;

import static org.junit.Assert.*;

public class SakktablaTest {

	@Test
	public void testTablabol_szamok() {
		int[] vart = {0,3,1,5,4,1,3,0,2,2,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,8,8,8,8,8,8,8,8,6,9,7,11,10,7,9,6};
		Sakktabla tabla = new Sakktabla(true, null);
		int[] szamok = tabla.tablabol_szamok();
		assertArrayEquals(vart, szamok);
	}

	@Test
	public void testJatek_vege() {
		int[] tabla_szamok = {-1,-1,-1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1};
		int[] tabla_szamok2 = {11,4,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1};
		int[] tabla_szamok3 = {0,-1,-1,-1,4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1};
		Sakktabla tabla = new Sakktabla(false, tabla_szamok);
		Sakktabla tabla2 = new Sakktabla(false, tabla_szamok2);
		Sakktabla tabla3 = new Sakktabla(false, tabla_szamok3);
		int vege = tabla.jatek_vege(true);
		int vege2 = tabla2.jatek_vege(false);
		int vege3 = tabla3.jatek_vege(true);
		assertEquals(1, vege);
		assertEquals(2, vege2);
		assertEquals(0, vege3);
	}

	@Test
	public void TestSetMezo(){
		Sakktabla tabla = new Sakktabla(true, null);
		tabla.setMezo(23, 8);
		int[] szamok = tabla.tablabol_szamok();
		assertEquals(8, szamok[23]);
	}
}
