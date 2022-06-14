package com.letscode.fiftycars.resource.locadora;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.service.locadora.iLocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "v1/locadora")
public class LocadoraResource {

    @Autowired
    private iLocadoraService service;

    @RequestMapping(value = "buscar_stream", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarMelhoresClientesStream() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarMelhoresClientesStream());
    }

    @RequestMapping(value = "buscar_jpa", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarMelhoresClientesJpa() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarMelhoresClientesJpa());
    }

    @RequestMapping(value = "buscar_jpa_dto", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteResponseDTO>> listarMelhoresClientesJpaDto() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarMelhoresClientesDto());
    }

    @RequestMapping(value = "buscar_distinct", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listarClientesDistinct() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarClientesDistinct());
    }

    @RequestMapping(value = "clientes/classificar", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> classificarClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.classificarClientes());
    }

    @RequestMapping(value = "alugar", method = RequestMethod.GET)
    public ResponseEntity alugarCarroThread() {
        service.alugarCarroThread(new Cliente(1, "Thread 1", null, null, 0));

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "alugar_async", method = RequestMethod.GET)
    public ResponseEntity alugarCarroAsync() {
        service.alugarCarroAsync(new Cliente(1, "Thread 1", null, null, 0));

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
