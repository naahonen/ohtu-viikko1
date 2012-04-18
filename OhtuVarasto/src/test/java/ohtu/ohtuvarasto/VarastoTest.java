
package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class VarastoTest {
 
    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
 
    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
 
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
 
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
 
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-8);

        // Vapaata tilaa pitäisi olla 10
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoidaLisÃ¤tÃ¤YliTilavuuden() {
        varasto.lisaaVarastoon(9001);

        // Saldon pitäisi olla vain 10, lisätään kaikki mitä voidaan. Loppu hukkaan
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
 
        double saatuMaara = varasto.otaVarastosta(2);
 
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenLisÃ¤Ã¤Tilaa() {
        varasto.lisaaVarastoon(8);
 
        varasto.otaVarastosta(2);
 
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void eiVoidaOttaaNegatiivista() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        // Meidän ei pitäisi saada mitään jos negatiivinen haku
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void pyydetÃ¤Ã¤nEnemmÃ¤nKuinOn() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(9);

        // Saadaan vain 8 vaikka pyydetään 9. Koska varastossa on vain 8.
        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void tulostusOikein() {
        assertEquals("saldo = 0.0, vielÃ¤ tilaa 10.0", varasto.toString());
    }

    @Test
    public void varastoaEiVoidaAlustaaNegatiiviseksi() {
        varasto = new Varasto(-5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoidaAlustaaNegatiiviseksi2() {
        varasto = new Varasto(-5.0, 5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoidaAlustaaVirheellisesti() {
        varasto = new Varasto(4, 5);
        assertEquals(4, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoidaAlustaaVirheellisesti2() {
        varasto = new Varasto(4, 5);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoidaAlustaaVirheellisesti3() {
        varasto = new Varasto(4, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoaEiVoidaAlustaaVirheellisesti4() {
        varasto = new Varasto(4, 3);
        assertEquals(3, varasto.getSaldo(), vertailuTarkkuus);
    }
}