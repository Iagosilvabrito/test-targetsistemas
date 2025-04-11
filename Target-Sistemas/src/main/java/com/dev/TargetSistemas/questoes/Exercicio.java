package com.dev.TargetSistemas.questoes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class Exercicio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== Questão 1 ==========");
        questao1();

        System.out.println("\n========== Questão 2 ==========");
        System.out.print("Informe um número para verificar se está na sequência de Fibonacci: ");
        questao2(scanner.nextInt());

        System.out.println("\n========== Questão 3 ==========");
        questao3();

        System.out.println("\n========== Questão 4 ==========");
        questao4();

        scanner.nextLine(); // consumir quebra de linha pendente
        System.out.println("\n========== Questão 5 ==========");
        System.out.print("Digite uma string para inverter: ");
        questao5(scanner.nextLine());

        scanner.close();
    }

    public static void questao1() {
        int soma = 0;
        for (int k = 1; k <= 13; k++) soma += k;
        System.out.println("Valor final de SOMA: " + soma);
    }

    public static void questao2(int numero) {
        int a = 0, b = 1;
        while (b < numero) {
            int temp = b;
            b += a;
            a = temp;
        }
        System.out.println(numero + (numero == 0 || b == numero
                ? " pertence à sequência de Fibonacci."
                : " NÃO pertence à sequência de Fibonacci."));
    }

    public static void questao3() {
        try (FileReader reader = new FileReader(Exercicio.class.getClassLoader()
                .getResource("faturamento.json").getFile())) {

            Gson gson = new Gson();
            List<DiaFaturamento> dias = gson.fromJson(reader, new TypeToken<List<DiaFaturamento>>() {}.getType());

            double soma = 0, menor = Double.MAX_VALUE, maior = Double.MIN_VALUE;
            int diasValidos = 0;

            for (DiaFaturamento dia : dias) {
                if (dia.valor > 0) {
                    soma += dia.valor;
                    menor = Math.min(menor, dia.valor);
                    maior = Math.max(maior, dia.valor);
                    diasValidos++;
                }
            }

            double media = soma / diasValidos;
            long acimaDaMedia = dias.stream().filter(d -> d.valor > media).count();

            System.out.printf("Menor valor de faturamento: R$ %.2f\n", menor);
            System.out.printf("Maior valor de faturamento: R$ %.2f\n", maior);
            System.out.printf("Dias com faturamento acima da média: %d\n", acimaDaMedia);

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo faturamento.json:");
            e.printStackTrace();
        }
    }

    public static void questao4() {
        double[] valores = {67836.43, 36678.66, 29229.88, 27165.48, 19849.53};
        String[] estados = {"SP", "RJ", "MG", "ES", "Outros"};
        double total = 0;

        for (double v : valores) total += v;

        for (int i = 0; i < valores.length; i++) {
            System.out.printf("%s: %.2f%%\n", estados[i], (valores[i] / total) * 100);
        }
    }

    public static void questao5(String texto) {
        StringBuilder sb = new StringBuilder(texto);
        System.out.println("String invertida: " + sb.reverse());
    }

    static class DiaFaturamento {
        int dia;
        double valor;
    }
}
