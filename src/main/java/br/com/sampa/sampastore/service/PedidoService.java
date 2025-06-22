package br.com.sampa.sampastore.service;

import br.com.sampa.sampastore.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> listarTodosOrdenadosPorData();
    Optional<Pedido> buscarPorId(Long id);
    void salvar(Pedido pedido);
}
