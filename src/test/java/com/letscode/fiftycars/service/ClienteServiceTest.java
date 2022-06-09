package com.letscode.fiftycars.service;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClientePOST;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.repository.cliente.ClienteRepository;
import com.letscode.fiftycars.service.cliente.ClienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void salvarClientTest() {
        ClientePOST clientePOST = new ClientePOST();
        clientePOST.setDataNascimento("2017-01-01");
        clientePOST.setNome("Hugo Brendow");
        clientePOST.setSexo('M');
        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(clientePOST));
        Assertions.assertEquals("O cliente precisa ter mais do que 18 anos!", error.getMessage());
    }

    @Test
    public void salvarClientGuinessTest() {
        ClientePOST clientePOST = new ClientePOST();
        clientePOST.setSexo('F');
        clientePOST.setNome("Elizabeth");
        clientePOST.setDataNascimento("1900-01-01");
        IllegalArgumentException error = Assertions.assertThrows(IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(clientePOST));
        Assertions.assertEquals("Esta pessoa deve entrar no guiness", error.getMessage());
    }

    @Test
    public void salvarClienteValido() {
        ClientePOST clientePOST = new ClientePOST();
        clientePOST.setDataNascimento("1997-01-01");
        clientePOST.setSexo('M');
        clientePOST.setNome("Hugo Brendow");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Hugo Brendow");
        cliente2.setSexo('M');
        cliente2.setCodigo(12);
        cliente2.setQuantidadeVisitas(100);
        cliente2.setDataNascimento(LocalDate.of(1997, 01, 01));

        Mockito.when(clienteRepository.save(any())).thenReturn(cliente2);

        Cliente cliente = clienteService.cadastrarCliente(clientePOST);
        Assert.assertEquals(cliente.getNome(), "Hugo Brendow");
        Assert.assertEquals(cliente.getSexo().charValue(), clientePOST.getSexo());
    }

    @Test
    public void listarClientesTest() {
        Cliente cliente = new Cliente(1, "Hugo", LocalDate.of(1997, 01, 01), 'M', 10);
        Cliente cliente2 = new Cliente(2, "Maria", LocalDate.of(1997, 01, 01), 'F', 12);
        List<Cliente> clienteList = List.of(cliente, cliente2);

        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);

        List<Cliente> clientes = clienteService.listarClientes();
        Assert.assertEquals(clientes.size(), clienteList.size());
    }

    @Test
    public void listarNomeClientesTest() {
        Cliente cliente = new Cliente(1, "Hugo", LocalDate.of(1997, 01, 01), 'M', 10);
        Cliente cliente2 = new Cliente(2, "Maria", LocalDate.of(1997, 01, 01), 'F', 12);
        List<Cliente> clienteList = List.of(cliente, cliente2);
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);

        List<String> nomes = clienteService.listarNomeClientes();
        Assert.assertEquals(nomes.get(0), clienteList.get(0).getNome().toUpperCase());
        Assert.assertEquals(nomes.get(1), clienteList.get(1).getNome().toUpperCase());
    }

    @Test
    public void listarNomeClientesErrorTest() {
        Mockito.when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> clienteService.listarNomeClientes());
    }

    @Test
    public void listarNomeClientesNullErrorTest() {
        Mockito.when(clienteRepository.findAll()).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> clienteService.listarNomeClientes());
    }

    @Test
    public void buscarClientePorNomeTest() {
        Cliente cliente = new Cliente(1, "Hugo", LocalDate.of(1997, 01, 01), 'M', 10);
        Cliente cliente2 = new Cliente(2, "Maria", LocalDate.of(1997, 01, 01), 'F', 12);
        List<Cliente> clienteList = List.of(cliente, cliente2);
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);

        Cliente clienteResponse = clienteService.buscarClientePorNome("Hugo");
        Assert.assertEquals(clienteResponse.getNome(), cliente.getNome());
    }

    @Test
    public void buscarClienteInexistentePorNomeTest() {
        Cliente cliente2 = new Cliente(2, "Maria", LocalDate.of(1997, 01, 01), 'F', 12);
        List<Cliente> clienteList = List.of(cliente2);
        Mockito.when(clienteRepository.findAll()).thenReturn(clienteList);

        Assertions.assertThrows(RuntimeException.class,
                () -> clienteService.buscarClientePorNome("Hugo"));
    }

    @Test
    public void buscarClientePorNomeJpaTest() {
        Cliente cliente = new Cliente(1, "Hugo", LocalDate.of(1997, 01, 01), 'M', 10);

        Mockito.when(clienteRepository.findClienteByNomeEquals("Hugo")).thenReturn(Optional.of(cliente));
        Cliente clienteResult = clienteService.buscarClientePorNomeJpa("Hugo");
        Assert.assertEquals(clienteResult.getNome(), "Hugo");
    }

    @Test
    public void buscarClientePorNomeJpaDtoTest() {
        Cliente cliente = new Cliente(1, "Hugo", LocalDate.of(1997, 01, 01), 'M', 10);
        Mockito.when(clienteRepository.findClienteByNomeEquals("Hugo")).thenReturn(Optional.of(cliente));
        ClienteResponseDTO clienteResponse = clienteService.buscarClientePorNomeJpaDto("Hugo");
        Assert.assertEquals(clienteResponse.getCliente(), "Hugo");
    }

    @Test
    public void buscarClientePorNomeJpaDtoErrorTest() {
//        Cliente cliente = new Cliente(1, "Hugo", LocalDate.of(1997, 01, 01), 'M', 10);
        Mockito.when(clienteRepository.findClienteByNomeEquals("Hugo")).thenReturn(Optional.empty());
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> clienteService.buscarClientePorNomeJpaDto("Hugo"));
        Assert.assertEquals(exception.getMessage(), "Cliente não encontrado");
    }
}
