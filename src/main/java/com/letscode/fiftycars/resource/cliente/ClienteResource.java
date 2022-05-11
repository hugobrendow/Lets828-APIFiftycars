package com.letscode.fiftycars.resource.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.service.cliente.iClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
