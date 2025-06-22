package br.com.sampa.sampastore.repository;

import br.com.sampa.sampastore.entity.Cliente;
import br.com.sampa.sampastore.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // busca pedidos de um cliente
     List<Pedido> findByClienteOrderByDataPedidoDesc(Cliente cliente);

    // busca todos os pedidos, ordenados por data decrescente
    List<Pedido> findAllByOrderByDataPedidoDesc();
}
