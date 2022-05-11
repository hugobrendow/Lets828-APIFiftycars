package com.letscode.fiftycars.service.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
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
            System.out.println("[LOG] >> Cliente encontrado.");
        });

        //Retorna, caso exista, o objeto, senão retorna uma exception (precisa extender a classe Throwable)
        return optionalCliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        //Retorna, caso exista, o objeto, senão retorna a instância definida no "orElse"
        //return optionalCliente.orElse(new Cliente(0, "Cliente desconhecido.", LocalDate.of(1900, 01, 01), '?'));
        
    }
}
