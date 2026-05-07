package Aplicacao;

import java.util.Scanner;
import Sistema.*;
import Utilidade.TratamentoEntrada;

public class MenuSistemaAdocao {
	static Scanner scan = new Scanner(System.in);
	static SistemaAdocao sistema = new SistemaAdocao();

	public static void main(String[] args) {

		int opcao, opcao1, total, media, idade;
		String tipo, nome, cpf, data, nomeAnimal, telefone, endereco, raca, cor, validacao;
		double porcetagemAdocao;
		boolean castrado = true;
		boolean vacinado = true;
		boolean validacaoEntrada = false;
		char sexo;

		do {
			System.out.println("\n ============================="
					+ "\n   SISTEMA DE ADOÇÃO DE PETS"
					+ "\n =============================");

			System.out.println("1. Cadastrar Animal\n"
					+ "2. Cadastrar Pessoa\n"
					+ "3. Realizar Adoção\n"
					+ "4. Listagens\n"
					+ "5. Estatísticas\n"
					+ "0. Finalizar operações");

			opcao = TratamentoEntrada.lerInt(scan, "Digite a opção: ", 0, Integer.MAX_VALUE);

			switch (opcao) {

				case 1:
					// Cadastrar animal
					tipo = TratamentoEntrada.lerString(scan, "Digite o tipo do animal (Cachorro|Gato|Outro): ");
					nome = TratamentoEntrada.lerString(scan, "Digite a nome do animal: ");
					idade = TratamentoEntrada.lerInt(scan, "Digite a idade do animal: ", 0, Integer.MAX_VALUE);
					sexo = TratamentoEntrada.lerSexo(scan, "Digite o sexo do animal (M/F):");
					raca = TratamentoEntrada.lerString(scan, "Digite a raça do animal: ");
					cor = TratamentoEntrada.lerString(scan, "Digite a cor do animal: ");
					castrado = TratamentoEntrada.lerRespostaSimNao(scan, "O animal é castrado (Sim/Não)? ");
					vacinado = TratamentoEntrada.lerRespostaSimNao(scan, "O animal é vacinado (Sim/Não)? ");
					sistema.addAnimal(scan, tipo, nome, idade, sexo, raca, cor, castrado, vacinado);
					break;

				case 2:
					// Cadastrar pessoa (adotante)
					nome = TratamentoEntrada.lerString(scan, "Digite o nome do adotante: ");
					cpf = TratamentoEntrada.lerCpf(scan, "Digite o cpf do adotante: ");
					telefone = TratamentoEntrada.lerString(scan, "Digite  o telefone do adotante: ");
					endereco = TratamentoEntrada.lerString(scan, "Digite o endereco: ");

					sistema.addAdotante(nome, cpf, telefone, endereco);
					break;

				case 3:
					// Realizar adoção
					cpf = TratamentoEntrada.lerCpf(scan, "Digite o cpf do adotante: ");
					nomeAnimal = TratamentoEntrada.lerString(scan, "Digite o nome do pet: ");
					data = TratamentoEntrada.lerString(scan, "Digite a data da adoção: ");
					sistema.addAdocao(cpf, nomeAnimal, data);
					break;

				case 4: // Submenu - Listagens
					do {
						System.out.println("\n== LISTAGENS ==");

						System.out.println("1. Listar Todos\n"
								+ "2. Listar Gatos disponíveis\n"
								+ "3. Listar Cachorros disponíveis\n"
								+ "4. Listar Outros animais disponíveis\n"
								+ "5. Listar por Gênero\n"
								+ "6. Listar Animais Castrados\n"
								+ "7. Listar Adoções Realizadas\n"
								+ "0. Voltar");
						opcao1 = TratamentoEntrada.lerInt(scan, "Escolha: ", 0, Integer.MAX_VALUE);
						switch (opcao1) {
							case 1:
								// Listar todos
								System.out.println(Listagem.listarAnimaisDisponiveis("animais.txt"));
								break;
							case 2:
								// Listar gatos
								System.out.println(Listagem.listarGatosDisponiveis("animais.txt"));
								break;
							case 3:
								// Listar cachorros
								System.out.println(Listagem.listarCachorrosDisponiveis("animais.txt"));
								break;
							case 4:
								// Listar outros animais
								System.out.println(Listagem.listarOutrosDisponiveis("animais.txt"));
								break;
							case 5:
								// Listar por gênero
								String especie = TratamentoEntrada.lerString(scan,
										"Digite a espécie de animal que deseja consultar: ");
								char gen = TratamentoEntrada.lerSexo(scan,
										"Digite o gênero que deseja consultar (M/F): ");

								System.out.println(Listagem.listarAnimaisPorGenero(especie, gen, "animais.txt"));

								break;
							case 6:
								// Listar castrados
								System.out.println(Listagem.listarAnimaisCastrados("animais.txt"));

								break;
							case 7:
								// Listar adoções
								System.out.println(Listagem.listarAdocoes("adocoes.txt"));
								break;
							case 0:
								// Voltar
								break;
							default:
								System.out.println("Opção inválida.");
								break;
						}
					} while (opcao1 != 0);
					break;

				case 5: // Submenu - Estatísticas
					do {
						System.out.println("\n== ESTATÍSTICAS ==");
						System.out.println("1. Total de Cachorros\n"
								+ "2. Total de Gatos\n"
								+ "3. Total de Outros Animais\n"
								+ "4. Media de idade aproximada dos animais disponiveis\n"
								+ "5. Porcentagem de adoções feitas\n"
								+ "0. Voltar");

						opcao1 = TratamentoEntrada.lerInt(scan, "Escolha: ", 0, Integer.MAX_VALUE);

						switch (opcao1) {
							case 1:
								// Total de cachorros
								total = sistema.totalCachorros();
								System.out.println(total);
								break;
							case 2:
								// Total de gatos
								total = sistema.totalGatinhos();
								System.out.println(total);
								break;
							case 3:
								// Total de outros animais
								total = sistema.totalOutros();
								System.out.println(total);
								break;
							case 4:
								// Media de idade dos animais que estão para adoção
								media = sistema.mediaIdadeAnimais();
								System.out.println(media);
								break;
							case 5:
								porcetagemAdocao = sistema.calcularPorcentagemAdocao();
								System.out.println(porcetagemAdocao);
								break;
							case 0:
								// Voltar
								break;
							default:
								System.out.println("Opção inválida.");
						}
					} while (opcao1 != 0);
					break;
				case 0:
					System.out.println("Encerrando o sistema.");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		} while (opcao != 0);
	}
}