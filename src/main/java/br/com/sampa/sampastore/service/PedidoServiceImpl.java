package br.com.sampa.sampastore.service;

import br.com.sampa.sampastore.entity.Pedido;
import br.com.sampa.sampastore.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listarTodosOrdenadosPorData() {
        return pedidoRepository.findAllByOrderByDataPedidoDesc();
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public void salvar(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

}
