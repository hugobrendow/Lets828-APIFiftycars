package com.letscode.fiftycars.service.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements iClienteService {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> listCli = new ArrayList<>();

        listCli.add(new Cliente(1, "Diego", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(2, "João", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(3, "Mario", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(4, "José", LocalDate.of(1995, 8, 13), 'M'));
        listCli.add(new Cliente(5, "Carlos", LocalDate.of(1995, 8, 13), 'M'));

        //Trabalhando com o Stream

        //Durante a construção teve um momento em que eu não estava conseguindo acessar os métodos
        //do objeto Cliente dentro da Lista (da pra conferir na gravação). Isso aconteceu, pois eu
        //havia esquecido de criar os getters e setters dos atributos. Nessa versão já deixei eles criados.

        /*return listCli.stream()
                .filter(x -> x.getCodigo() == 1)
                .collect(Collectors.toList());*/

        return listCli;
    }
}
