package br.com.kvervandi.geo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraGeometrica calc = new CalculadoraGeometrica();

        System.out.println("=== Kvervandi Geometry System ===");
        System.out.println("1. Descobrir lado (Lei dos Cossenos)");
        System.out.println("2. Descobrir ângulo (dados 3 lados)");
        System.out.print("Escolha: ");
        
        if (scanner.hasNextInt()) {
            int opcao = scanner.nextInt();
            try {
                if (opcao == 1) {
                    System.out.print("Lado A: "); double a = scanner.nextDouble();
                    System.out.print("Lado B: "); double b = scanner.nextDouble();
                    System.out.print("Ângulo (graus): "); double ang = scanner.nextDouble();
                    System.out.println("Resultado Lado C = " + calc.calcularTerceiroLado(a, b, ang));
                } else if (opcao == 2) {
                    System.out.print("Lado Oposto: "); double o = scanner.nextDouble();
                    System.out.print("Lado Adj 1: "); double ad1 = scanner.nextDouble();
                    System.out.print("Lado Adj 2: "); double ad2 = scanner.nextDouble();
                    System.out.println("Resultado Ângulo = " + calc.calcularAngulo(o, ad1, ad2));
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
