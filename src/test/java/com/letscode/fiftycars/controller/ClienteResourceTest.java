package com.letscode.fiftycars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.fiftycars.domain.cliente.Cliente;
import com.letscode.fiftycars.resource.cliente.ClienteResource;
import com.letscode.fiftycars.service.cliente.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteResourceTest {
    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteResource clienteResource;
    @Autowired
    private MockMvc mockMvc;

    private List<Cliente> clienteList;
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteResource).build();

        cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.now());
        cliente.setNome("Hugo");
        cliente.setCodigo(1);
        cliente.setQuantidadeVisitas(12);
        cliente.setSexo('M');
        clienteList = new ArrayList<>();
        clienteList.add(cliente);
    }

    @Test
    public void listarProdutosHttpTest() throws Exception {
        when(clienteService.listarClientes()).thenReturn(clienteList);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/clientes").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(cliente))).
                andDo(MockMvcResultHandlers.print());
        verify(clienteService).listarClientes();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}