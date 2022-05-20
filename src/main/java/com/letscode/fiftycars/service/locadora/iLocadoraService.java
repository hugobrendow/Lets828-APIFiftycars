package com.letscode.fiftycars.service.locadora;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;

import java.util.List;

public interface iLocadoraService {

    List<Cliente> listarMelhoresClientesStream();

    List<Cliente> listarMelhoresClientesJpa();

    List<ClienteResponseDTO> listarMelhoresClientesDto();

    List<String> listarClientesDistinct();

    List<Cliente> classificarClientes();

    void alugarCarroThread(Cliente cliente);

    void alugarCarroAsync(Cliente cliente);
}
