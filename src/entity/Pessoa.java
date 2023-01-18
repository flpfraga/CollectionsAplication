package entity;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Pessoa {

	protected String nome;
	
	protected LocalDate nascimento;
	
	public Pessoa() {
		this.nome="";
		this.nascimento = LocalDate.now();
	}

	public Pessoa(String nome, LocalDate nascimento) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nascimento, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(nascimento, other.nascimento) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", nascimento=" + nascimento + "]";
	}
	
	
}
