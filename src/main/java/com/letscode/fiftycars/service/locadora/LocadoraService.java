package com.letscode.fiftycars.service.locadora;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class LocadoraService implements iLocadoraService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarMelhoresClientesStream() {
        return clienteRepository.findAll().stream()
                //Reversed -> vai inverter a ordem de ordenação padrão (crescente)
                .sorted(Comparator.comparingInt(Cliente::getQuantidadeVisitas).reversed())
                //Skip -> pular a leitura de algum dado do stream
                .skip(1)
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> listarMelhoresClientesJpa() {
        return clienteRepository.findTop3ByOrderByQuantidadeVisitasDesc();
    }

    @Override
    public List<ClienteResponseDTO> listarMelhoresClientesDto() {
        return clienteRepository.findTop3ByOrderByQuantidadeVisitasDesc().stream()
                .map(cliente -> new ClienteResponseDTO(
                        cliente.getNome(),
                        cliente.getDataNascimento().toString(),
                        cliente.getSexo()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listarClientesDistinct() {
        return clienteRepository.findAll().stream()
                .map(x -> {
                    //alguma logica anterior, opcional
                    return x.getNome();
                })
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> classificarClientes() {

        List<Cliente> listaClientes = clienteRepository.findAll();

        //COUNT
        System.out.println("Quantidade de clientes dentro do Stream: " + listaClientes.stream().count());

        //ALL MATCH / ANY MATCH / NONE MATCH
        System.out.println("Todos os clientes são do gênero masculino? " + listaClientes.stream().allMatch(x -> x.getSexo().equals('M')));

        //MIN
        System.out.println("Pessoa mais jovem: " + listaClientes.stream()
                .min((c1,c2) -> (int) (ChronoUnit.YEARS.between(c1.getDataNascimento(), LocalDate.now()) - ChronoUnit.YEARS.between(c2.getDataNascimento(), LocalDate.now()))));

        //MAX
        System.out.println("Pessoa mais jovem: " + listaClientes.stream()
                .max((c1,c2) -> (int) (ChronoUnit.YEARS.between(c1.getDataNascimento(), LocalDate.now()) - ChronoUnit.YEARS.between(c2.getDataNascimento(), LocalDate.now()))));

        //REDUCE
        System.out.println("Trabalhando com informações em pares: " + listaClientes.stream()
                .map(x -> ChronoUnit.YEARS.between(x.getDataNascimento(), LocalDate.now()))
                .reduce((c1,c2) -> c1 + c2)
                .get());

        //FOREACH
        listaClientes.stream().forEach(System.out::println);

        return null;

    }

    @Override
    public void alugarCarroThread(Cliente cliente) {
        Emprestimo emprestimo = new Emprestimo(cliente);
        Thread thread = new Thread(emprestimo);
        thread.start();

        //System.out.println(emprestimo.isAlive());
        //emprestimo.join();


        emprestimo = new Emprestimo(new Cliente(2, "Thread 2", null, null, 0));
        Thread thread2 = new Thread(emprestimo);
        thread2.start();


        System.out.println("[" + Thread.currentThread().getName() + "] - Threads liberadas");
    }

    @Override
    public void alugarCarroAsync(Cliente cliente) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> alugarCarroAssincrono(cliente));

        //completableFuture.cancel(true);
        //completableFuture.getNow(100);
        //completableFuture.isCancelled();

        String msg = "Processando";
        while(!completableFuture.isDone()) {
            System.out.println(msg += ".");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String alugarCarroAssincrono(Cliente cliente) {
        System.out.println(LocalDateTime.now() + " - [" + Thread.currentThread().getName() + "] - Processando empréstimo...");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Erro ao alugar.";
        }

        System.out.println(LocalDateTime.now() + " - [" + Thread.currentThread().getName() + "] - Carro emprestado para: " + cliente.getNome());
        return "Veículo alugado com sucesso.";
    }
}
