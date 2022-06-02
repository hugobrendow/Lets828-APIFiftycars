package com.letscode.fiftycars.resource.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.dto.cliente.ClientePOST;
import com.letscode.fiftycars.dto.cliente.ClientePUT;
import com.letscode.fiftycars.dto.cliente.ClienteResponseDTO;
import com.letscode.fiftycars.service.cliente.ClienteService;
import com.letscode.fiftycars.service.cliente.iClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "v1/clientes")
public class ClienteResource {

    @Autowired
    private iClienteService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> listarClientes() {
        List<Cliente> list = service.listarClientes();

        return ResponseEntity.status(HttpStatus.OK).body(list.toString()); //service.listarClientes().toString()
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarClientesJson() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarClientes());
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
            /*return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Cliente.builder()
                    .nome("Diego")
                    .dataNascimento(LocalDate.now())
                    .quantidadeVisitas(0)
                    .build());*/
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Cliente(null, e.getMessage(), null, null, 0));
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
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClientePOST cliente){
        Cliente temp = service.cadastrarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> excluirCliente(@RequestParam Integer codigo){
        try {
            service.excluirCliente(codigo);

            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClientePUT cliente){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarCliente(cliente));

    }

}
