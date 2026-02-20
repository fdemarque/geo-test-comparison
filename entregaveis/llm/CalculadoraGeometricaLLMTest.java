// package br.com.kvervandi.geo;

// import org.junit.Test;
// import org.junit.Assert;
// import static org.junit.Assert.*;

// public class CalculadoraGeometricaLLMTest {

//     private final CalculadoraGeometrica calc = new CalculadoraGeometrica();
//     private final double DELTA = 0.001;

//     @Test
//     public void testTrianguloRetangulo() {
//         double ladoC = calc.calcularTerceiroLado(3, 4, 90);
//         Assert.assertEquals("Hipotenusa de 3-4 deve ser 5", 5.0, ladoC, DELTA);
//     }

//     @Test
//     public void testTrianguloEquilatero() {
//         double ladoC = calc.calcularTerceiroLado(10, 10, 60);
//         Assert.assertEquals(10.0, ladoC, DELTA);
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void testLadoNegativo() {
//         calc.calcularTerceiroLado(-5, 10, 90);
//     }

//     @Test
//     public void testAnguloEquilatero() {
//         double angulo = calc.calcularAngulo(10, 10, 10);
//         Assert.assertEquals(60.0, angulo, DELTA);
//     }
// }

package br.com.kvervandi.geo;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculadoraGeometricaLLMTest {

    private static final double DELTA = 1e-9;
    private final CalculadoraGeometrica calc = new CalculadoraGeometrica();

    // --- TESTES DE CASOS NORMAIS E BORDAS ---
    @Test
    public void deveCalcularTerceiroLadoComAnguloZero() {
        assertEquals(0.0, calc.calcularTerceiroLado(10, 10, 0), DELTA);
    }

    @Test
    public void deveCalcularTerceiroLadoComAnguloReto() {
        assertEquals(5.0, calc.calcularTerceiroLado(3, 4, 90), DELTA);
    }

    @Test
    public void deveCalcularTerceiroLadoComAnguloRaso() {
        assertEquals(12.0, calc.calcularTerceiroLado(5, 7, 180), DELTA);
    }

    // --- TESTES RESISTENTES A MUTATION TESTING ---
    @Test
    public void deveValidarPrecisaoDoCalculoSemPermitirAlteracaoDeSinal() {
        double resultado = calc.calcularTerceiroLado(7, 9, 120);
        double esperado = Math.sqrt(7*7 + 9*9 - 2*7*9*Math.cos(Math.toRadians(120)));
        assertEquals(esperado, resultado, DELTA);
        assertTrue("Resultado deve ser estritamente positivo", resultado > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveFalharQuandoSomaForExatamenteIgualAoLadoOposto() {
        // Valida a barreira da desigualdade triangular
        calc.calcularAngulo(10, 5, 5);
    }

    @Test
    public void deveGarantirQueAnguloSempreFiqueEntreZeroECentoEOitenta() {
        double angulo = calc.calcularAngulo(5, 3, 4);
        assertTrue(angulo >= 0);
        assertTrue(angulo <= 180);
    }

    // --- PROPERTY-BASED TESTING (Simulado) ---
    @Test
    public void propriedadeLeiDosCossenosDeveSerSimetrica() {
        for (int i = 1; i <= 100; i++) {
            double c1 = calc.calcularTerceiroLado(i, i + 1, 45);
            double c2 = calc.calcularTerceiroLado(i + 1, i, 45);
            assertEquals(c1, c2, DELTA);
        }
    }

    @Test
    public void propriedadeAnguloDeTrianguloEquilateroSempreSessentaGraus() {
        for (int i = 1; i <= 100; i++) {
            assertEquals(60.0, calc.calcularAngulo(i, i, i), DELTA);
        }
    }
}