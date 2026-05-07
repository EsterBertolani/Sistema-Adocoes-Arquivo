package Utilidade;
import java.util.Scanner;

public class TratamentoEntrada {

	public static String lerString(Scanner scan, String msg) {
		String scanString;
		do {
			System.out.println(msg);
			scanString = scan.nextLine().trim();
			if(scanString.isEmpty()) {
				System.out.println("Essa estrada não pode estar vazia");
			}

		}while(scanString.isEmpty());
		return scanString;
	}

	public static int lerInt(Scanner scan, String msg, int min, int max) {
		int valor = 0;
		boolean valido;
		do {
			valido = true;
			try {
				System.out.println(msg);
				valor = Integer.parseInt(scan.nextLine());
				if (valor < min || valor > max) {
					System.out.println("Valor fora do intervalo permitido");
					valido = false;
				}
			}catch(NumberFormatException e) {
				System.out.println("Entrada invalida");
				valido = false;
			}

		} while(!valido);
		return valor;
	}
	public static boolean lerRespostaSimNao(Scanner scanner, String pergunta) {
	    String resposta;
	    while (true) {
	        System.out.print(pergunta + " (Sim/Não): ");
	        resposta = scanner.nextLine().trim().toLowerCase();

	        if (resposta.equals("sim")) {
	            return true;
	        } else if (resposta.equals("não") || resposta.equals("nao")) {
	            return false;
	        } else {
	            System.out.println("Entrada inválida. Por favor, responda com 'Sim' ou 'Não'.");
	        }
	    }
	}
	public static char lerSexo(Scanner scanner, String pergunta) {
        String entrada;
        while (true) {
            System.out.print(pergunta + " (M/F): ");
            entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.equals("M") || entrada.equals("F")) {
                return entrada.charAt(0);
            } else {
                System.out.println("Entrada inválida. Digite 'M' para macho ou 'F' para fêmea.");
            }
        }
    }
	public static String lerCpf(Scanner scanner, String pergunta) {
	    String cpf;
	    while (true) {
	        System.out.print(pergunta + " (apenas números): ");
	        cpf = scanner.nextLine().replaceAll("\\D", ""); // remove tudo que não for dígito

	        if (cpf.length() == 11) {
	            return cpf;
	        } else {
	            System.out.println("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
	        }
	    }
	}
}