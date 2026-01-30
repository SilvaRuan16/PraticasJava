package com.praticas;

import java.util.Scanner;

public class Main {

    /// Método para criar linha de marcação entre os conteúdos
    public static void repetidor(String con, int qtd, String breakLine) {
        if (breakLine.contains("\n")) {
            System.out.println(breakLine + con.repeat(qtd) + breakLine);
        }

        if (!"\n".equals(breakLine)) {
            System.out.println("" + con.repeat(qtd) + "");
        }
    }

    /// Método para criar o painel inicial da aplicação
    public static void painelInicial() {
        String mensagem = "BEM VINDO AO SUPER CALCULADORA";
        repetidor("#", mensagem.length() + 4, "\n");
        System.out.println(mensagem);
        repetidor("#", mensagem.length() + 4, "\n");
    }

    /// Método para calcular diretamente
    public static void calculadoraDireto(Scanner scan) {

        try {
            String conta;
            boolean operacaoValida = false;
            // Vetores para capturar o sinal de operação
            String[] sum, sub, div, mul;
            // Variaveis de calculo
            double soma, subtracao, divisao, multiplicacao;

            while (!operacaoValida) {
                System.out.println("Informe uma conta (ex: 1 + 2):");
                conta = scan.nextLine().replace(" ", "");

                operacaoValida = conta.contains("+") || conta.contains("-") || conta.contains("/")
                        || conta.contains("*");

                if (operacaoValida) {
                    if (conta.contains("+")) {
                        sum = conta.split("\\+");
                        soma = (Double.parseDouble(sum[0]) + Double.parseDouble(sum[1]));
                        repetidor("+", 15, "\n");
                        System.out.println("Resultado: " + soma);
                    }
                    if (conta.contains("-")) {
                        sub = conta.split("\\-");
                        subtracao = (Double.parseDouble(sub[0]) - Double.parseDouble(sub[1]));
                        repetidor("+", 15, "\n");
                        System.out.println("Resultado: " + subtracao);
                    }
                    if (conta.contains("/")) {
                        div = conta.split("\\/");
                        divisao = (Double.parseDouble(div[0]) / Double.parseDouble(div[1]));
                        repetidor("+", 15, "\n");
                        System.out.println("Resultado: " + divisao);
                    }
                    if (conta.contains("*")) {
                        mul = conta.split("\\*");
                        multiplicacao = (Double.parseDouble(mul[0]) * Double.parseDouble(mul[1]));
                        repetidor("+", 15, "\n");
                        System.out.println("Resultado: " + multiplicacao);
                    }
                } else {
                    System.err.println("Operacao invalida! Tente novamente.");
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Houve uma exception => " + e);
        }
    }

    /// Método para calcular dinamicamente
    public static void calculadoraDinamica(Scanner scan) {
        try {

            double valor1, valor2;
            String operacao;
            // Variaveis de calculo
            double soma, subtracao, divisao, multiplicacao;

            System.out.println("Informe o valor 1:");
            valor1 = scan.nextDouble();
            scan.nextLine();

            System.out.println("Informe a operacao:");
            operacao = scan.nextLine();

            boolean operacaoValida = operacao.equals("+") || operacao.equals("-") || operacao.equals("/")
                    || operacao.equals("*");

            while (!operacaoValida) {
                repetidor("+", 15, "\n");
                System.err.println("Operacao invalida! Tente novamente");
                System.out.println("Informe a operacao:");
                operacao = scan.nextLine();
                scan.nextLine();
                repetidor("+", 15, "\n");
            }

            System.out.println("Informe o valor 2:");
            valor2 = scan.nextDouble();
            scan.nextLine();

            switch (operacao) {
                case "+" -> {
                    soma = valor1 + valor2;
                    repetidor("+", 15, "\n");
                    System.out.println("Resultado:" + soma);
                }
                case "-" -> {
                    subtracao = valor1 - valor2;
                    repetidor("+", 15, "\n");
                    System.out.println("Resultado:" + subtracao);
                }
                case "/" -> {
                    divisao = valor1 / valor2;
                    repetidor("+", 15, "\n");
                    System.out.println("Resultado:" + divisao);
                }
                case "*" -> {
                    multiplicacao = valor1 * valor2;
                    repetidor("+", 15, "\n");
                    System.out.println("Resultado:" + multiplicacao);
                }
                default -> System.err.println("Operacao invalida! Tente novamente.");
            }

        } catch (Exception e) {
            System.err.println("Houve uma exception =>" + e);
        }
    }

    /// Método para escolher o tipo da calculadora
    private static void escolha(int num, Scanner scan) {
        switch (num) {
            case 1 -> calculadoraDireto(scan);
            case 2 -> calculadoraDinamica(scan);
            case 3 -> System.out.println("Encerrando...");
            default -> System.out.println("Opcao invalida! Tente novamente.");
        }
    }

    @SuppressWarnings("UnnecessaryContinue")
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            int opcao = 0;
            painelInicial();
            do {
                repetidor("+", 15, "\n");
                System.out.println("Escolha o tipo de calculadora:");
                System.out.println("[1 - Direto  ]. Ex: (1 + 2) ou (3.5 - 1.17)");
                System.out.println("[2 - Dinamico]. Ex: Escolha o valor 1 = (1,19)");
                System.out.println("[3 - Sair    ].");
                repetidor("+", 15, "\n");

                if (scan.hasNextInt()) {
                    opcao = scan.nextInt();
                    scan.nextLine();
                    escolha(opcao, scan);
                } else {
                    System.err.println("Entrada inválida! Insira um numero.");
                    scan.nextLine();
                    continue;
                }
            } while (opcao != 3);

            scan.close();
        } catch (Exception e) {
            System.err.println("Houve uma exception => " + e);
        }
    }
}