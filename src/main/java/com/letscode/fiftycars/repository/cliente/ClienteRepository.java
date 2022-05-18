package com.letscode.fiftycars.repository.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //SELECT * FROM TB_CLIENTES WHERE NOM_CLI == 'Diego Neri'
    Optional<Cliente> findClienteByNomeEquals(String nome);

    //SELECT * FROM TB_CLIENTES WHERE NOM_CLI == 'Diego Neri' and SEX_CLI == 'M'
    Optional<Cliente> findClienteByNomeEqualsAndSexoEquals(String nome, Character sexo);

    //SELECT TOP 3 * FROM TB_CLIENTE ORDER BY QTD_VST DESC
    List<Cliente> findTop3ByOrderByQuantidadeVisitasDesc();

}
