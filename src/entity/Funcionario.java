package entity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Funcionario extends Pessoa{
	
	private Double salario;
	
	private String funcao;
	
	public Funcionario() {
		super();
		this.salario = 0.0;
		this.funcao = "";
	}

	public Funcionario(String nome, LocalDate nascimento, Double salario, String funcao) {
		super(nome, nascimento);
		this.salario = salario;
		this.funcao = funcao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(funcao, salario);
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
		Funcionario other = (Funcionario) obj;
		return Objects.equals(funcao, other.funcao) && Objects.equals(salario, other.salario);
	}

	@Override
	public String toString() {
		
		
				
		return "Funcionario [Nome= " + nome + ", Nascimento= " +nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salario= " + converterDoubleParaValor(salario) + ", Funcao=" + funcao + "]";
	}
	
	public String converterDoubleParaValor(Double salario) {
		
		Locale localBR = new Locale("pt","BR");
		
		NumberFormat valor = NumberFormat.getCurrencyInstance(localBR);
		
		return valor.format(salario);
		
	}
	
	
	
	
	

}
