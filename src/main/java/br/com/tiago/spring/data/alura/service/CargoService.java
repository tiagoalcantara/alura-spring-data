package br.com.tiago.spring.data.alura.service;

import br.com.tiago.spring.data.alura.models.Cargo;
import br.com.tiago.spring.data.alura.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        while(system) {
            System.out.println("Qual ação executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Inserir");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int opcao = scanner.nextInt();
            switch(opcao) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
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
        System.out.print("Descrição do cargo: ");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println("Salvo!");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Id: ");
        long id = scanner.nextLong();

        System.out.print("Descrição: ");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);

        cargoRepository.save(cargo);
        System.out.println("Atualizado!");
    }

    private void visualizar() {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner){
        System.out.print("Id: ");
        long id = scanner.nextLong();

        cargoRepository.deleteById(id);
        System.out.println("Deletado!");
    }
}
