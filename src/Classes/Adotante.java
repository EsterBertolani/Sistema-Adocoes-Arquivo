package Classes;

import java.util.Objects;

public class Adotante {

	protected String nome;
	protected String cpf;
	protected String telefone;
	protected String endereco;

	public Adotante() {
		super();
		this.nome = "";
		this.cpf = "";
		this.telefone = "";
		this.endereco = "";
	}

	public Adotante(String nome, String cpf, String telefone, String endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, endereco, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adotante other = (Adotante) obj;
		return Objects.equals(cpf, other.cpf) 
				&& Objects.equals(endereco, other.endereco) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s", nome, cpf, telefone, endereco);
	}

	public String toCSV() {
		return nome + "," + cpf + "," + telefone + "," + endereco;
	}

}
