package Classes;

import java.util.Objects;

public class Adocao {

	private String nomePet;
	private String cpfAdotante;
	private String dataAdocao;

	public Adocao() {
		super();
		this.nomePet = "";
		this.cpfAdotante = "";
		this.dataAdocao = "";
	}

	public Adocao(String nomePet, String cpfAdotante, String dataAdocao) {
		super();
		this.nomePet = nomePet;
		this.cpfAdotante = cpfAdotante;
		this.dataAdocao = dataAdocao;
	}


	public String getNomePet() {
		return nomePet;
	}
	public void setNomePet(String nomePet) {
		this.nomePet = nomePet;
	}
	public String getCpfAdotante() {
		return cpfAdotante;
	}
	public void setCpfAdotante(String cpfAdotante) {
		this.cpfAdotante = cpfAdotante;
	}
	public String getDataAdocao() {
		return dataAdocao;
	}
	public void setDataAdocao(String dataAdocao) {
		this.dataAdocao = dataAdocao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfAdotante, dataAdocao, nomePet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adocao other = (Adocao) obj;
		return Objects.equals(cpfAdotante, other.cpfAdotante) && Objects.equals(dataAdocao, other.dataAdocao)
				&& Objects.equals(nomePet, other.nomePet);
	}

	@Override
	public String toString() {
		return "=== Adoção ===\n "
				+ "Pet adotado: " + nomePet + "\n"
				+ "Adotante: " + cpfAdotante + "\n"
				+ "Data da adocao: " + dataAdocao + "\n";
	}

	public String toCSV() {
		return String.format("%s, %s, %s", nomePet, cpfAdotante, dataAdocao);

	}

	public static Adocao fromCSV(String linha) {
		String[] partes = linha.split(",");

		if (partes.length < 3) {
			throw new IllegalArgumentException("Linha CSV inválida: " + linha);
		}

		String nomePet = partes[0].trim();
		String cpfAdotante = partes[1].trim();
		String dataAdocao = partes[2].trim();

		return new Adocao(nomePet, cpfAdotante, dataAdocao);
	}
}
