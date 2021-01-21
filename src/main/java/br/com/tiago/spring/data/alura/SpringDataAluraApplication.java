package br.com.tiago.spring.data.alura;

import br.com.tiago.spring.data.alura.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataAluraApplication implements CommandLineRunner {

	private Boolean system = true;


	private final CargoService cargoService;
	private final UnidadeTrabalhoService unidadeTrabalhoService;
	private final FuncionarioService funcionarioService;
	private final RelatoriosService relatoriosService;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

	public SpringDataAluraApplication(CargoService cargoService, UnidadeTrabalhoService unidadeTrabalhoService, FuncionarioService funcionarioService, RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.funcionarioService = funcionarioService;
		this.relatoriosService = relatoriosService;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while(system) {
			System.out.println("Qual ação você quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidades de Trabalho");
			System.out.println("3 - Funcionários");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatório Funcionário Dinâmico");

			int action = scanner.nextInt();

			switch (action) {
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 3:
					funcionarioService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}
}
