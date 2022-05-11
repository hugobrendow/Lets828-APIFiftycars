package com.letscode.fiftycars.repository.cliente;

import com.letscode.fiftycars.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
