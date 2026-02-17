package br.com.kvervandi.geo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraGeometricaLlmTest {

    private final CalculadoraGeometrica calc = new CalculadoraGeometrica();
    private final double DELTA = 0.001;

    @Test
    void testTrianguloRetangulo() {
        // Triângulo 3-4-5 clássico, ângulo de 90 graus entre 3 e 4
        double ladoC = calc.calcularTerceiroLado(3, 4, 90);
        assertEquals(5.0, ladoC, DELTA, "Hipotenusa de triângulo 3-4 deve ser 5");
    }

    @Test
    void testTrianguloEquilateroLado() {
        // Lados 10 e 10 com ângulo de 60 deve dar 10
        double ladoC = calc.calcularTerceiroLado(10, 10, 60);
        assertEquals(10.0, ladoC, DELTA, "Triângulo equilátero deve ter 3º lado igual");
    }

    @Test
    void testCalcularAnguloEquilatero() {
        // Todos lados iguais -> ângulos devem ser 60
        double angulo = calc.calcularAngulo(10, 10, 10);
        assertEquals(60.0, angulo, DELTA, "Angulo de equilátero deve ser 60 graus");
    }

    @Test
    void testCalcularAnguloReto() {
        // Oposto ao lado 5 (hipotenusa) deve ser 90 graus
        double angulo = calc.calcularAngulo(5, 3, 4);
        assertEquals(90.0, angulo, DELTA, "Angulo oposto a hipotenusa deve ser 90");
    }

    @Test
    void testLadoNegativoException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calcularTerceiroLado(-5, 10, 90);
        });
    }

    @Test
    void testDesigualdadeTriangular() {
        // Lado 20 não fecha com 5 e 5 (5+5 < 20)
        assertThrows(IllegalArgumentException.class, () -> {
            calc.calcularAngulo(20, 5, 5);
        });
    }
}
