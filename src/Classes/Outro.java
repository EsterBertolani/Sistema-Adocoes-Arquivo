package Classes;

import java.util.Objects;

public class Outro extends Animal {

	private String especie;

	public Outro() {
		super();
		this.especie = "";
	}

	public Outro(String nome, int idade, char genero, String raca, String cor, boolean castrado, boolean vacinado,
			String especie) {

		super(nome, idade, genero, raca, cor, castrado, vacinado);
		this.especie = especie;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String espécie) {
		this.especie = espécie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(especie);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Outro other = (Outro) obj;
		return Objects.equals(especie, other.especie);
	}

	@Override
	public String toString() {
		return "Espécie: " + especie + " | " + super.toString();
	}

	@Override
	public String toCSV() {
		return String.format("%s,%s",
				super.toCSV(),
				especie);
	}

	public static Outro fromCSV(String linha) {
		String[] partes = linha.split(",");

		String nome = partes[1].trim();
		int idade = Integer.parseInt(partes[2].trim());
		char genero = partes[3].trim().charAt(0);
		String raca = partes[4].trim();
		String cor = partes[5].trim();
		boolean castrado = Boolean.parseBoolean(partes[6].trim());
		boolean vacinado = Boolean.parseBoolean(partes[7].trim());

		String especie = partes.length > 9 ? partes[9].trim() : "";

		return new Outro(nome, idade, genero, raca, cor,
				castrado, vacinado, especie);
	}

}
