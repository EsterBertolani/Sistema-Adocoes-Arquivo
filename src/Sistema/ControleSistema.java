package Sistema;
import java.io.*;
import java.util.ArrayList;
import Classes.*;

public class ControleSistema {
	ArrayList <Adotante> adotantes = new ArrayList<>(); 
	ArrayList <Animal> animais = new ArrayList<>();
	ArrayList <Adocao> adocoes = new ArrayList<>();

	public void salvarAnimais() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("animais.txt", false));
			for (Animal salvar : animais) {
				bw.write(salvar.toCSV());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void carregarAnimais() {
		ArrayList<Animal> ani = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("animais.txt"))){
			String linha;
			Animal animal;

			while((linha = br.readLine()) != null) {
				String [] div = linha.split(",");
				String tipo = div[0];
				String nome = div[1];
				int idade = Integer.parseInt(div[2]);
				char sexo = div[3].charAt(0);
				String raca = div[4];
				String cor = div[5];
				boolean castrado = Boolean.parseBoolean(div[6]);
				boolean vacinado = Boolean.parseBoolean(div[7]);

				switch(tipo.toLowerCase()) {
				case "cachorro":
					String porte = div[8];
					boolean adestrado = Boolean.parseBoolean(div[9]);
					boolean vermifugado = Boolean.parseBoolean(div[10]);
					animal = new Cachorro(nome, idade, sexo, raca, cor, castrado, vacinado, porte, adestrado, vermifugado);
					break;
				case "gato":
					boolean arisco = Boolean.parseBoolean(div[8]);
					boolean temFiv = Boolean.parseBoolean(div[9]);
					boolean temFelv = Boolean.parseBoolean(div[10]);
					animal = new Gato(nome, idade, sexo, raca, cor, castrado, vacinado, arisco, temFiv, temFelv);
					break;
				default:
					String especie = div[8];
					animal = new Outro(nome, idade, sexo, raca, cor, castrado, vacinado,especie);
					break;
				}
				ani.add(animal);
			}
			animais = ani;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvaAdotante() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("adotantes.txt", false));
			for (Adotante salvar : adotantes) {
				bw.write(salvar.toCSV());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void carregarAdotantes() {
		ArrayList<Adotante> adot = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("adotantes.txt"))) {
			String line;
			Adotante adotante;
			while ((line = br.readLine()) != null) {
				String[] div = line.split(",");
				String nome = div[0];
				String cpf = div[1];
				String telefone = div[2];
				String endereco = div[3];
				adotante = new Adotante(nome,cpf,telefone,endereco);
				adot.add(adotante);
			}
			adotantes = adot;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvarAdocoes() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("adocoes.txt"))) {
			for (Adocao a : adocoes) {
				bw.write(a.toCSV());   // "nomePet, cpfAdotante, dataAdocao"
				bw.newLine();
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar adoções", e);
		}
	}

	/**
	 * Lê adocoes.txt, converte cada linha em Adocao e popula
	 * a lista interna. Linhas mal formatadas são ignoradas com warning.
	 */
	public void carregarAdocoes() {
		adocoes.clear();

		try (BufferedReader br = new BufferedReader(new FileReader("adocoes.txt"))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				linha = linha.trim();
				if (linha.isEmpty()) continue;

				try {
					Adocao nova = Adocao.fromCSV(linha);
					adocoes.add(nova);
				} catch (IllegalArgumentException iae) {
					System.err.println("Ignorada linha inválida em adocoes.txt: " + linha);
				}
			}
			System.out.println("Adoções carregadas com sucesso: " + adocoes.size());
		} catch (FileNotFoundException fnfe) {
			System.out.println("Arquivo adocoes.txt não encontrado. Nenhuma adoção carregada.");
		} catch (IOException ioe) {
			throw new RuntimeException("Erro ao carregar adoções", ioe);
		}
	}




	public static ArrayList<Animal> listarAnimais(String caminho) {
		ArrayList<Animal> animais = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				Animal a = Animal.fromCSV(linha);
				animais.add(a);
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return animais;
	}
}
