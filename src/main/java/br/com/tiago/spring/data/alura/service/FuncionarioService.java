package br.com.tiago.spring.data.alura.service;

import br.com.tiago.spring.data.alura.models.Cargo;
import br.com.tiago.spring.data.alura.models.Funcionario;
import br.com.tiago.spring.data.alura.models.UnidadeTrabalho;
import br.com.tiago.spring.data.alura.repository.CargoRepository;
import br.com.tiago.spring.data.alura.repository.FuncionarioRepository;
import br.com.tiago.spring.data.alura.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class FuncionarioService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual acao de cargo deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }

        }

    }

    private void salvar(Scanner scanner) {
        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Long cargoId = scanner.nextLong();

        List<UnidadeTrabalho> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(new BigDecimal(salario));
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadesTrabalho(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner) {
        boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            long unidadeId = scanner.nextLong();

            if(unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidade.ifPresent(unidades::add);
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }

    private void atualizar(Scanner scanner) {
        System.out.println("Digite o id");
        Long id = scanner.nextLong();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Long cargoId = scanner.nextLong();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(new BigDecimal(salario));
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual a página?");
        int page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "nome"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        System.out.println(funcionarios);
        System.out.println("Página atual: " + funcionarios.getNumber() + 1);
        System.out.println("Total de páginas: " + funcionarios.getTotalPages());

        funcionarios.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.println("Id");
        Long id = scanner.nextLong();
        funcionarioRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
