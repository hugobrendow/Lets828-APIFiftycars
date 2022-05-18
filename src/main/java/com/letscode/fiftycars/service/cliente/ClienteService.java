package com.letscode.fiftycars.service.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClientePOST;
import com.letscode.fiftycars.dto.cliente.ClientePUT;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.repository.cliente.ClienteRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService implements iClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        /*List<Cliente> listCli = new ArrayList<>();

        listCli.add(new Cliente(1, "Diego", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(2, "João", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(3, "Mario", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(4, "José", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(5, "Carlos", LocalDate.of(1995, 8, 13), 'M'));

        //Trabalhando com o Stream

        //Durante a construção teve um momento em que eu não estava conseguindo acessar os métodos
        //do objeto Cliente dentro da Lista (da pra conferir na gravação). Isso aconteceu, pois eu
        //havia esquecido de criar os getters e setters dos atributos. Nessa versão já deixei eles criados.

        return listCli.stream()
                .filter(x -> x.getCodigo() == 1)
                .collect(Collectors.toList());

        //return listCli;
         */

        return clienteRepository.findAll();
    }

    @Override
    public List<String> listarNomeClientes() {
        return clienteRepository.findAll().stream()
                .map(x -> x.getNome().toUpperCase())
                .collect(Collectors.toList());
    }

    @Override
    public Cliente buscarClientePorNome(String nome) {
        Optional<Cliente> optionalCliente = clienteRepository.findAll().stream()
                .filter(x -> x.getNome().equals(nome)) //Diego Neri
                .findFirst();

        //Existe um objeto dentro do optional
        /*if(optionalCliente.isPresent()){
            return optionalCliente.get();
        } else {
            //qualquer outra lógica
        }*/

        //Checar existencia de valor e determinar uma função para ser executada
        optionalCliente.ifPresent(value -> {
            System.out.println("[LOG] >> Cliente encontrado. " + value);
        });

        //Retorna, caso exista, o objeto, senão retorna uma exception (precisa extender a classe Throwable)
        return optionalCliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        //Retorna, caso exista, o objeto, senão retorna a instância definida no "orElse"
        //return optionalCliente.orElse(new Cliente(0, "Cliente desconhecido.", LocalDate.of(1900, 01, 01), '?'));

    }

    @Override
    public ClienteResponseDTO buscarClientePorNomeJpaDto(String nome) {
        //return clienteRepository.findClienteByNomeEquals(nome).get();

        Cliente cli = clienteRepository.findClienteByNomeEquals(nome).get();

        return new ClienteResponseDTO(cli.getNome(), cli.getDataNascimento().toString(), cli.getSexo());
    }

    @Override
    public Cliente buscarClientePorNomeJpa(String nome) {
        return clienteRepository.findClienteByNomeEquals(nome).get();
    }

    @Override
    public Cliente cadastrarCliente(ClientePOST cliente) {
        //Implementa suas regras de negócio
        Cliente tempCli = new Cliente(null, cliente.getNome(), LocalDate.parse(cliente.getDataNascimento()), cliente.getSexo(), 0);

        return clienteRepository.save(tempCli);
    }

    @Override
    public void excluirCliente(Integer codigo) {
        //Opção 0
        clienteRepository.deleteById(codigo);

        //Opção 1
        clienteRepository.findById(codigo).ifPresent(
                x -> clienteRepository.deleteById(x.getCodigo())
        );

        //Opção 2
        clienteRepository.findById(codigo).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado")
        );

        clienteRepository.deleteById(codigo);
    }

    @Override
    public Cliente atualizarCliente(ClientePUT cliente) {

        return clienteRepository.save(
                new Cliente(cliente.getCodigo(),
                        cliente.getNome(),
                        LocalDate.parse(cliente.getDataNascimento()),
                        cliente.getSexo(),
                        0));
        //TODO: Bug na quantidade visitas, em todo o update vai voltar pra zero.
    }

    @Override
    public void registrarVisitaCliente(Integer codigo) {
        Cliente tempCliente = clienteRepository.findById(codigo).get();
        //tempCliente++;
        tempCliente.setQuantidadeVisitas(tempCliente.getQuantidadeVisitas() + 1);

        clienteRepository.save(tempCliente);
    }


}
