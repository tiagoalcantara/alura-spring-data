package br.com.tiago.spring.data.alura.service;

import br.com.tiago.spring.data.alura.models.Funcionario;
import br.com.tiago.spring.data.alura.repository.FuncionarioRepository;
import br.com.tiago.spring.data.alura.specifications.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        System.out.println("Digite o nome");
        String nome = scanner.next();
        if(nome.equalsIgnoreCase("NULL")) {
            nome = null;
        }

        System.out.println("Digite o cpf");
        String cpf = scanner.next();
        if(cpf.equalsIgnoreCase("NULL")) {
            cpf = null;
        }

        System.out.println("Digite o salario");
        BigDecimal salario = scanner.nextBigDecimal();
        if(salario.equals(BigDecimal.ZERO)) {
            salario = null;
        }

        System.out.println("Digite a data");
        String data = scanner.next();
        LocalDate dataContratacao;
        if(data.equalsIgnoreCase("NULL")) {
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios =
                funcionarioRepository.findAll(Specification.where(
                        SpecificationFuncionario.nome(nome))
                        .or(SpecificationFuncionario.cpf(cpf))
                        .or(SpecificationFuncionario.salario(salario))
                        .or(SpecificationFuncionario.dataContratacao(dataContratacao))
                );

        funcionarios.forEach(System.out::println);
    }
}
