package br.com.kvervandi.geo;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

public class CalculadoraGeometricaLLMTest {

    private final CalculadoraGeometrica calc = new CalculadoraGeometrica();
    private final double DELTA = 0.001;

    @Test
    public void testTrianguloRetangulo() {
        double ladoC = calc.calcularTerceiroLado(3, 4, 90);
        Assert.assertEquals("Hipotenusa de 3-4 deve ser 5", 5.0, ladoC, DELTA);
    }

    @Test
    public void testTrianguloEquilatero() {
        double ladoC = calc.calcularTerceiroLado(10, 10, 60);
        Assert.assertEquals(10.0, ladoC, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLadoNegativo() {
        calc.calcularTerceiroLado(-5, 10, 90);
    }

    @Test
    public void testAnguloEquilatero() {
        double angulo = calc.calcularAngulo(10, 10, 10);
        Assert.assertEquals(60.0, angulo, DELTA);
    }
}
