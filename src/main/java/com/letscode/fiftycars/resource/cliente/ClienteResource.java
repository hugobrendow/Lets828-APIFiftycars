package com.letscode.fiftycars.resource.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClientePOST;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.service.cliente.iClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/clientes")
public class ClienteResource {

    @Autowired
    private iClienteService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> listarClientes() {
        List<Cliente> list = service.listarClientes();

        return ResponseEntity.status(HttpStatus.OK).body(list.toString()); //service.listarClientes().toString()
    }

    @RequestMapping(value = "nomes", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listarNomesClientes() {

        return ResponseEntity.status(HttpStatus.OK).body(service.listarNomeClientes());
    }

    @RequestMapping(value = "buscar", method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscarClientePorNome() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.buscarClientePorNome("Diego Neri2"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Cliente(null, e.getMessage(), null, null));
        }

    }

    //http://localhost:8080/v1/clientes/buscar_jpa_dto?nome=Diego%20Neri&sexo=M
    @RequestMapping(value = "buscar_jpa_dto", method = RequestMethod.GET)
    public ResponseEntity<ClienteResponseDTO> buscarClientePorNomeJpaDto(@RequestParam String nome, @RequestParam Character sexo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarClientePorNomeJpaDto(nome));
    }

    //http://localhost:8080/v1/clientes/buscar_jpa?nome=Diego%20Neri&sexo=M
    @RequestMapping(value = "buscar_jpa", method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscarClientePorNomeJpa(@RequestParam String nome, @RequestParam Character sexo) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarClientePorNomeJpa(nome));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClientePOST cliente){
        Cliente temp = service.cadastrarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

}
