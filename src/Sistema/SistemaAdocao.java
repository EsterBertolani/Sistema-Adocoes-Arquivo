package Sistema;
import java.util.Scanner;
import Classes.*;
import Utilidade.TratamentoEntrada;

public class SistemaAdocao extends ControleSistema{
	Adotante adotante;
	Animal animal;

	public void addAdotante(String nome, String cpf, String telefone, String endereco) {
		carregarAdotantes();
		boolean cpfExiste = false;
		for(Adotante a: adotantes) {
			if(a.getCpf().equals(cpf)) {
				cpfExiste = true;
				break;
			}
		}
		if(cpfExiste) {
			System.out.println("Já existe um adotante com esse CPF");
		} else {
			adotante = new Adotante(nome,cpf,telefone, endereco);
			adotantes.add(adotante);
			System.out.println("Adotante cadastrado com sucesso!");
			salvaAdotante();
		}

	}

	public void addAnimal(String tipo,String nome,int idade,char sexo,String raca,String cor,
			boolean castrado,boolean vacinado) {
		carregarAnimais();
		Scanner sc = new Scanner(System.in);
		
		// Verifica se já existe um animal com mesmo nome e tipo
		for (Animal a : animais) {
			if (a.getNome().equalsIgnoreCase(nome) && a.getClass().getSimpleName().equalsIgnoreCase(tipo)) {
				System.out.println("Já existe um " + tipo + " cadastrado com o nome '" + nome + "'.");
				return;
			}
		}

		Animal novoAnimal = null;

		switch (tipo) {
		case "cachorro":
			String porte = TratamentoEntrada.lerString(sc, "Qual o porte do cachorro: ");
			boolean adestrado = TratamentoEntrada.lerRespostaSimNao(sc, "O animal é adestrado |Sim ou Não|? ");
			boolean vermifugado = TratamentoEntrada.lerRespostaSimNao(sc, "O animal é vermifugado |Sim ou Não|? ");

			novoAnimal = new Cachorro(nome, idade, sexo, raca, cor, castrado, vacinado, porte, adestrado, vermifugado);
			break;

		case "gato":
			boolean arisco = TratamentoEntrada.lerRespostaSimNao(sc, "O gato é arisco |Sim ou Não|? ");
			boolean fiv = TratamentoEntrada.lerRespostaSimNao(sc, "O gato Possui FIV |Sim ou Não|? ");
			boolean felv = TratamentoEntrada.lerRespostaSimNao(sc, "O gato possui FELV |Sim ou Não|? ");

			novoAnimal = new Gato(nome, idade, sexo, raca, cor, castrado, vacinado, arisco, fiv, felv);
			break;

		case "outro":
			String especie = TratamentoEntrada.lerString(sc,"Digite a especie do animal: ");
			novoAnimal = new Outro(nome, idade, sexo, raca, cor, castrado, vacinado, especie);
			break;

		default:
			System.out.println("Tipo inválido. Animal não cadastrado.");
			return;
		}

		animais.add(novoAnimal);
		salvarAnimais();
		System.out.println("Animal do tipo " + tipo + " cadastrado com sucesso!");
	}

	public void addAdocao(String cpfAdotanteParam, String nomeAnimalParam, String dataAdocao) {
		// 1) Carrega tudo que precisa
		carregarAdotantes();
		carregarAnimais();
		carregarAdocoes();

		// 2) Normaliza entradas
		String cpfLimpo = cpfAdotanteParam.replaceAll("\\D", "").trim();
		String nomeAnimalLimpo = nomeAnimalParam.trim();

		// 3) Busca o adotante pelo CPF
		Adotante adotanteSelecionado = adotantes.stream()
				.filter(a -> a.getCpf().replaceAll("\\D", "").equalsIgnoreCase(cpfLimpo))
				.findFirst()
				.orElse(null);

		if (adotanteSelecionado == null) {
			System.out.printf("Adotante com CPF %s não encontrado.%n", cpfAdotanteParam);
			return;
		}

		// 4) Busca o animal disponível pelo nome
		Animal animalSelecionado = animais.stream()
				.filter(a -> a.getNome().equalsIgnoreCase(nomeAnimalLimpo))
				.findFirst()
				.orElse(null);

		if (animalSelecionado == null) {
			System.out.printf("Animal com nome %s não encontrado.%n", nomeAnimalParam);
			return;
		}

		// 5) Monta o novo registro de adoção
		Adocao nova = new Adocao(
				animalSelecionado.getNome(),
				adotanteSelecionado.getCpf(),
				dataAdocao
				);

		// 6) Remove o animal e persiste mudanças
		animais.remove(animalSelecionado);
		salvarAnimais();

		// 7) Adiciona na lista em memória e salva só a nova
		adocoes.add(nova);
		salvarAdocoes();

		System.out.println("Adoção registrada com sucesso!");
	}

	public void removerAdocao(String nomePet, String cpfAdotante) {
		carregarAdocoes(); // carrega a lista atual
		boolean removido = adocoes.removeIf(a ->
		a.getNomePet().equalsIgnoreCase(nomePet.trim()) &&
		a.getCpfAdotante().replaceAll("\\D", "").equals(cpfAdotante.replaceAll("\\D", ""))
				);

		if (removido) {
			salvarAdocoes(); // regrava o arquivo com a lista atualizada
			System.out.println("Adoção removida com sucesso.");
		} else {
			System.out.println("Adoção não encontrada.");
		}
	}



	//------------------ nat metodos de estatistica
	public int totalCachorros() {
		carregarAnimais();
		int total =0;
		for(int i=0; i<animais.size(); i++) {
			if (animais.get(i) instanceof Cachorro) { //tive q usar o instanceof por q trata de herança
				total++;       //ele so conta o "obj" cachorro
			}
		}
		return total;
	}

	public int totalGatinhos() {
		carregarAnimais();
		int total =0;
		for (int i=0; i<animais.size(); i++) {
			if (animais.get(i) instanceof Gato) {
				total++;
			}
		}
		return total;
	}

	public int totalOutros() {
		carregarAnimais();
		int total=0;
		for(int i=0; i<animais.size(); i++) {
			if (animais.get(i) instanceof Outro) {
				total++;
			}
		}
		return total;
	}

	public int mediaIdadeAnimais() {
		carregarAnimais();
		if (animais.isEmpty()) {
			System.out.println("Nenhum animal cadastrado.");
			return 0;
		}
		int soma = 0;
		for (Animal a : animais) {
			System.out.println();
			soma += a.getIdade();
		}
		int media =  soma / animais.size();
		System.out.printf("A Média de idade dos animais é: %d anos%n" , media);
		return media;
	}

	public double calcularPorcentagemAdocao() {
		carregarAnimais();  // Essa parte vai lsitar os animais disponíveis
		carregarAdocoes();  // E aqui vai carregar as adoções

		int totalAnimais = animais.size() + adocoes.size(); // vai pegar o total de diposníveis + adoção

		if (totalAnimais == 0) {
			return 0.0; // condicional para evitar a divisão por zero
		}

		int totalAdotados = adocoes.size();

		double porcentagem = (totalAdotados * 100.0) / totalAnimais;
		return porcentagem;
	}

}
