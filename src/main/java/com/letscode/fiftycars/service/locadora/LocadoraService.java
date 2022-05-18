package com.letscode.fiftycars.service.locadora;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
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
}
