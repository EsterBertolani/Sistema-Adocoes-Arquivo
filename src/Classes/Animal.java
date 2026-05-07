package Classes;

import java.util.Objects;

public class Animal {

	protected String nome;
	protected int idade;
	protected char genero;
	protected String raca;
	protected String cor;

	protected boolean castrado;
	protected boolean vacinado;
	protected boolean adotado = false;

	public Animal() {
		super();
		this.nome = "";
		this.idade = 0;
		this.genero = '\0';
		this.raca = "";
		this.cor = "";
		this.castrado = false;
		this.vacinado = false;
		this.adotado = false;
	}

	public Animal(String nome, int idade, char genero, String raca, String cor, boolean castrado, boolean vacinado) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.raca = raca;
		this.cor = cor;
		this.castrado = castrado;
		this.vacinado = vacinado;
		this.adotado = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getCorPelo() {
		return cor;
	}

	public void setCorPelo(String corPelo) {
		this.cor = corPelo;
	}

	public boolean isCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

	public boolean isVacinado() {
		return vacinado;
	}

	public void setVacinado(boolean vacinado) {
		this.vacinado = vacinado;
	}

	public boolean isAdotado() {
		return adotado;
	}

	public void setAdotado(boolean adotado) {
		this.adotado = adotado;
	}

	@Override
	public String toString() {
		return String.format("%s,%d,%s,%s,%s,%s,"
				+ "%s", nome, idade, genero, cor, raca, (castrado ? "Sim" : "Não"),
				(vacinado ? "Sim" : "Não"));
	}

	public String toCSV() {
		return String.format("%s,%s,%d,%c,%s,%s,%s,%s",
				this.getClass().getSimpleName().toLowerCase(), // identifica o tipo
				nome,
				idade,
				genero,
				raca,
				cor,
				castrado,
				vacinado);
	}

	public static Animal fromCSV(String linha) {
		String[] partes = linha.split(",");

		if (partes.length == 0) {
			throw new IllegalArgumentException("Linha inválida: " + linha);
		}

		String tipo = partes[0].trim().toLowerCase();

		switch (tipo) {
			case "gato":
				return Gato.fromCSV(linha);
			case "cachorro":
				return Cachorro.fromCSV(linha);
			case "outro":
				return Outro.fromCSV(linha);
			default:
				throw new IllegalArgumentException("Tipo de animal desconhecido: " + tipo);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(adotado, castrado, cor, genero, idade, nome, raca, vacinado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return adotado == other.adotado && castrado == other.castrado && Objects.equals(cor, other.cor)
				&& genero == other.genero && idade == other.idade && Objects.equals(nome, other.nome)
				&& Objects.equals(raca, other.raca) && vacinado == other.vacinado;
	}

}
