package com.letscode.fiftycars.resource.locadora;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.service.locadora.iLocadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
