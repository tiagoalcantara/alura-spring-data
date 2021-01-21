package br.com.tiago.spring.data.alura.service;

import br.com.tiago.spring.data.alura.models.Funcionario;
import br.com.tiago.spring.data.alura.models.FuncionarioProjecao;
import br.com.tiago.spring.data.alura.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual ação executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionários por nome");
            System.out.println("2 - Busca funcionários por nome, salário maior que e data de admissão");
            System.out.println("3 - Busca funcionários data de admissão posterior");
            System.out.println("4 - Lista salários");

            int opcao = scanner.nextInt();
            switch(opcao) {
                case 1:
                    buscaFuncionarioPorNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacaoPosterior(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioPorNome(Scanner scanner) {
        System.out.print("Busca (nome): ");
        String nome = scanner.next();

        List<Funcionario> resultado = funcionarioRepository.findByNome(nome);
        resultado.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();

        System.out.print("Data contratação: ");
        String dataContratacao = scanner.next();
        LocalDate localDate = LocalDate.parse(dataContratacao, formatter);

        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository.findByNomeSalarioMaiorDataContratacao(nome, new BigDecimal(salario), localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacaoPosterior(Scanner scanner) {
        System.out.print("Data contratação: ");
        String dataContratacao = scanner.next();
        LocalDate localDate = LocalDate.parse(dataContratacao, formatter);

        List<Funcionario> list = funcionarioRepository.findByDataContratacaoPosterior(localDate);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioBySalario();
        list.forEach(item -> System.out.println("---\n" + item.getId()
        + "\n" + item.getNome() + "\n" + item.getSalario() + "\n---\n"));
    }

}
