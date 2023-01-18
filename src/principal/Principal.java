package principal;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import Arquivo.FuncionarioLerArquivo;
import comparator.FuncionarioNomeComparator;
import entity.Funcionario;

public class Principal {

	public static Set<String> listarFuncoes(Set<Funcionario> funcionarios) {

		Set<String> funcionariosSet = new TreeSet<>();
		funcionarios.forEach(f -> funcionariosSet.add(f.getFuncao()));
		return funcionariosSet;
	}

	public static Set<Funcionario> listarFuncionariosPorFuncao(Set<Funcionario> funcionarios, String funcao) {

		return funcionarios.stream().filter(f -> f.getFuncao().equalsIgnoreCase(funcao)).collect(Collectors.toSet());

	}

	public static Map<String, Set<Funcionario>> agrupar(Set<Funcionario> funcionarios) {

		Map<String, Set<Funcionario>> funcionariosAgrupados = new HashMap<>();

		Set<String> funcionariosSet = listarFuncoes(funcionarios);

		funcionariosSet.forEach(f -> funcionariosAgrupados.put(f, listarFuncionariosPorFuncao(funcionarios, f)));

		return funcionariosAgrupados;
	}
	
	public static Integer calcularIdade(LocalDate nascimento) {
		
		LocalDate agora = LocalDate.now();
		
		Period idade = Period.between(nascimento, agora);
		
		return idade.getYears();
	}

	public static void main(String[] args) {

		FuncionarioLerArquivo lerFuncionario = new FuncionarioLerArquivo();
		Set<Funcionario> funcionarios;
		try {
			// inserindo funcionarios na set list
			funcionarios = lerFuncionario.ler("data.csv");

			// Removendo João da Lista
			funcionarios.removeIf(f -> f.getNome().equals("Joao"));

			// Imprimindo todos os funcionarios
			funcionarios.forEach(f -> System.out.println(f));

			// Aplicando aumento de 10% para os funcionarios
			funcionarios.forEach(f -> f.setSalario(f.getSalario() * 1.1));

			// Agrupando funcionarios em função MAP
			Map<String, Set<Funcionario>> funcionariosAgrupados = agrupar(funcionarios);
			
			//Imprimindo funcionarios agrupados por função
			Set<Entry<String, Set<Funcionario>>> set = funcionariosAgrupados.entrySet();
			Iterator it = set.iterator();

			while (it.hasNext()) {
				System.out.println();
				Entry<String, Set<Funcionario>> entry = (Entry) it.next();
				System.out.println("Funcao: " + entry.getKey());
				entry.getValue().forEach(f -> System.out.println(f));
			}
			
			//Imprimir funcionarios que fazem aniversario entre os meses 10 e 12
			System.out.println("Funcionarios que fazem aniversario entre os meses 10 e 12");
			Funcionario funcionario = new Funcionario();
			Double totalSalario = 0.0;
			for (Funcionario f : funcionarios) {
				int mes = f.getNascimento().getMonth().getValue();
				if(mes >= 10){
					System.out.println(f);
				}
				if (funcionario.getNascimento().isAfter(f.getNascimento())) {
					funcionario =f;
				}
				totalSalario+=f.getSalario();
			}
			
			//Imprimir nome e idade do funcionario com maior idade
			System.out.println();
			System.out.println("Funcionario mais velho:");
			System.out.println("Nome " + funcionario.getNome() + " ,Idade: " + calcularIdade(funcionario.getNascimento()));
			
			//Imprimir lista por ordem alfabética
			System.out.println();
			System.out.println("Imprimir em ordem alfabetica");
			List<Funcionario> funcionarioList = new ArrayList<>(funcionarios);
			funcionarioList.sort(new FuncionarioNomeComparator());
			funcionarioList.forEach(f -> System.out.println(f));
			
			//Imprimir total dos salarios dos funcionarios
			Locale localBR = new Locale("pt","BR");
			NumberFormat valor = NumberFormat.getCurrencyInstance(localBR);
			System.out.println();
			System.out.println("Total dos saários dos funcionarios");
			System.out.println(valor.format(totalSalario));
			
			//Salário minimo por funcionario
			System.out.println();
			System.out.println("Salario minimo por funcionario");
			funcionarios.forEach(f -> System.out.println(f.getNome() + ", recebe: "+ (f.getSalario().intValue()/1212) + " salarios minimos"));
		} catch (IOException e) {

			System.out.println("Não foi possível ler do arquivo e criar a lista de funcionario. " + e.getMessage());
		}

	}

}
