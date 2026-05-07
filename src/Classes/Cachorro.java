package Classes;

import java.util.Objects;

public class Cachorro extends Animal {

	private String porte;
	private boolean adestrado;
	private boolean vermifugado;

	public Cachorro() {
		super();
		this.porte = "";
		this.adestrado = false;
		this.vermifugado = false;
	}

	public Cachorro(String nome, int idade, char genero, String raca, String cor, boolean castrado, boolean vacinado,
			String porte, boolean adestrado, boolean vermifugado) {

		super(nome, idade, genero, raca, cor, castrado, vacinado);

		this.porte = porte;
		this.adestrado = adestrado;
		this.vermifugado = vermifugado;

	}

	public String getPorte() {
		return porte;
	}

	public void setPorte(String porte) {
		this.porte = porte;
	}

	public boolean isAdestrado() {
		return adestrado;
	}

	public void setAdestrado(boolean adestrado) {
		this.adestrado = adestrado;
	}

	public boolean isVermifugado() {
		return vermifugado;
	}

	public void setVermifugado(boolean vermifugado) {
		this.vermifugado = vermifugado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(adestrado, porte, vermifugado);
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
		Cachorro other = (Cachorro) obj;
		return adestrado == other.adestrado && Objects.equals(porte, other.porte) && vermifugado == other.vermifugado;
	}

	@Override
	public String toString() {
		return String.format("Espécie: Cachorro | %sPorte: %s | É adestrado: %s | Foi vermifugado: %s",
				super.toString(),
				porte,
				adestrado,
				vermifugado);
	}

	@Override
	public String toCSV() {
		return String.format("%s,%s,%b,%b",
				super.toCSV(),
				porte,
				adestrado,
				vermifugado);
	}

	public static Cachorro fromCSV(String linha) {
		String[] partes = linha.split(",");

		String nome = partes[1].trim();
		int idade = Integer.parseInt(partes[2].trim());
		char genero = partes[3].trim().charAt(0);
		String raca = partes[4].trim();
		String cor = partes[5].trim();
		boolean castrado = Boolean.parseBoolean(partes[6].trim());
		boolean vacinado = Boolean.parseBoolean(partes[7].trim());

		String porte = partes[8].trim();
		boolean adestrado = Boolean.parseBoolean(partes[9].trim());
		boolean vermifugado = Boolean.parseBoolean(partes[10].trim());

		return new Cachorro(nome, idade, genero, raca, cor,
				castrado, vacinado, porte, adestrado, vermifugado);

	}

}
