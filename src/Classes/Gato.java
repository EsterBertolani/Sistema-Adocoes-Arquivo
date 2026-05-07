package Classes;

import java.util.Objects;

public class Gato extends Animal {

	private boolean arisco;
	private boolean temFiv;
	private boolean temFelv;

	public Gato() {
		super();
		this.arisco = false;
		this.temFiv = false;
		this.temFelv = false;
	}

	public Gato(String nome, int idade, char genero, String raca, String cor, boolean castrado, boolean vacinado,
			boolean arisco, boolean temFiv, boolean temFelv) {

		super(nome, idade, genero, raca, cor, castrado, vacinado);
		this.arisco = arisco;
		this.temFiv = temFiv;
		this.temFelv = temFelv;
	}

	public boolean isArisco() {
		return arisco;
	}

	public void setArisco(boolean arisco) {
		this.arisco = arisco;
	}

	public boolean isTemFiv() {
		return temFiv;
	}

	public void setTemFiv(boolean temFiv) {
		this.temFiv = temFiv;
	}

	public boolean isTemFelv() {
		return temFelv;
	}

	public void setTemFelv(boolean temFelv) {
		this.temFelv = temFelv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(arisco, temFelv, temFiv);
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
		Gato other = (Gato) obj;
		return arisco == other.arisco && temFelv == other.temFelv && temFiv == other.temFiv;
	}

	@Override
	public String toString() {
		return "Espécie: Gato | " + super.toString() + String.format("É arisco: %s | Tem FIV: %s | Tem FeLV: %s",
				arisco, temFiv, temFelv);
	}

	@Override
	public String toCSV() {
		return String.format("%s,%b,%b,%b",
				super.toCSV(),
				arisco,
				temFiv,
				temFelv);
	}

	public static Gato fromCSV(String linha) {
		String[] partes = linha.split(",");

		String nome = partes[1].trim();
		int idade = Integer.parseInt(partes[2].trim());
		char genero = partes[3].trim().charAt(0);
		String raca = partes[4].trim();
		String cor = partes[5].trim();
		boolean castrado = Boolean.parseBoolean(partes[6].trim());
		boolean vacinado = Boolean.parseBoolean(partes[7].trim());

		boolean arisco = Boolean.parseBoolean(partes[8].trim());
		boolean temFiv = Boolean.parseBoolean(partes[9].trim());
		boolean temFelv = Boolean.parseBoolean(partes[10].trim());

		return new Gato(nome, idade, genero, raca, cor,
				castrado, vacinado, arisco, temFiv, temFelv);
	}

}
