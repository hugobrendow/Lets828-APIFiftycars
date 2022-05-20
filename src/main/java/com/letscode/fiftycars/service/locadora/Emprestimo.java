package com.letscode.fiftycars.service.locadora;

import com.letscode.fiftycars.domain.cliente.Cliente;

import java.time.LocalDateTime;

public class Emprestimo implements Runnable { //extends Thread

    private Cliente cliente;

    public Emprestimo(Cliente cliente) {
        this.cliente = cliente;
    }

    public void run() {
        System.out.println(LocalDateTime.now() + " - [" + Thread.currentThread().getName() + "] - Processando empréstimo...");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(LocalDateTime.now() + " - [" + Thread.currentThread().getName() + "] - Carro emprestado para: " + cliente.getNome());
    }
}
