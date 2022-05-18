package com.letscode.fiftycars.service.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClientePOST;
import com.letscode.fiftycars.dto.cliente.ClientePUT;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;

import java.util.List;

public interface iClienteService {

    List<Cliente> listarClientes();

    List<String> listarNomeClientes();

    Cliente buscarClientePorNome(String nome);

    ClienteResponseDTO buscarClientePorNomeJpaDto(String nome);

    Cliente buscarClientePorNomeJpa(String nome);

    Cliente cadastrarCliente(ClientePOST cliente);

    void excluirCliente(Integer codigo);

    Cliente atualizarCliente(ClientePUT cliente);

    void registrarVisitaCliente(Integer codigo);
}
