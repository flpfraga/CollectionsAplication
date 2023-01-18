package comparator;

import java.util.Comparator;

import entity.Funcionario;

public class FuncionarioNomeComparator implements Comparator<Funcionario> {

	@Override
	public int compare(Funcionario f1, Funcionario f2) {
		return f1.getNome().compareToIgnoreCase(f2.getNome());
	}

}
