package Arquivo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import entity.Funcionario;

public class FuncionarioLerArquivo extends Arquivo {
	
    public Set <Funcionario> ler (String caminho) throws IOException{
        
    	Set <Funcionario> funcionarios = new LinkedHashSet <>();
        
        openTextFile(caminho);
        getLinhaArquivo().readLine();
        while (getLinhaArquivo().ready()) {
            String aux = getLinhaArquivo().readLine();
            String linha [] = aux.split(";");
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(linha[0]);
            LocalDate data = LocalDate.parse(linha[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            funcionario.setNascimento(data);
            Double salario = Double.parseDouble(linha[2]);
            funcionario.setSalario(salario);
            funcionario.setFuncao(linha[3]);
            
            funcionarios.add(funcionario);
        }
        closeTextFile();
        return funcionarios;
    }
	
}
