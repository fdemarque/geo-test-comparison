package br.com.kvervandi.geo;

public class CalculadoraGeometrica {

    public double calcularTerceiroLado(double ladoA, double ladoB, double anguloGraus) {
        if (ladoA <= 0 || ladoB <= 0) {
            throw new IllegalArgumentException("Lados devem ser positivos");
        }
        
        double anguloRadianos = Math.toRadians(anguloGraus);
        
        // Lei dos Cossenos: c^2 = a^2 + b^2 - 2ab*cos(C)
        double ladoCQuadrado = (ladoA * ladoA) + (ladoB * ladoB) - (2 * ladoA * ladoB * Math.cos(anguloRadianos));
        
        return Math.sqrt(ladoCQuadrado);
    }

    public double calcularAngulo(double ladoOposto, double ladoAdjacente1, double ladoAdjacente2) {
        if (ladoOposto <= 0 || ladoAdjacente1 <= 0 || ladoAdjacente2 <= 0) {
             throw new IllegalArgumentException("Lados devem ser positivos");
        }
        
        if (ladoAdjacente1 + ladoAdjacente2 <= ladoOposto || 
            ladoAdjacente1 + ladoOposto <= ladoAdjacente2 || 
            ladoOposto + ladoAdjacente2 <= ladoAdjacente1) {
            throw new IllegalArgumentException("Não forma um triângulo (Desigualdade Triangular)");
        }

        double numerador = (ladoAdjacente1 * ladoAdjacente1) + (ladoAdjacente2 * ladoAdjacente2) - (ladoOposto * ladoOposto);
        double denominador = 2 * ladoAdjacente1 * ladoAdjacente2;
        
        double cosAngulo = numerador / denominador;
        
        // Correção de precisão double
        if (cosAngulo > 1.0) cosAngulo = 1.0;
        if (cosAngulo < -1.0) cosAngulo = -1.0;

        double radianos = Math.acos(cosAngulo);
        return Math.toDegrees(radianos);
    }
}
