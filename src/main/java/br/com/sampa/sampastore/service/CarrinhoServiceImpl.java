package br.com.sampa.sampastore.service;

import br.com.sampa.sampastore.entity.Carrinho;
import br.com.sampa.sampastore.entity.Produto;
import br.com.sampa.sampastore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public CarrinhoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Carrinho adicionarItem(Carrinho carrinho, Long produtoId, int quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new IllegalArgumentException("Quantidade indisponível em estoque");
        }

        carrinho.adicionarItem(produto, quantidade);
        return carrinho;
    }

    @Override
    public Carrinho atualizarQuantidade(Carrinho carrinho, Long produtoId, int quantidade) {
        if (quantidade <= 0) {
            carrinho.removerItem(produtoId);
            return carrinho;
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        // Validação de estoque atualizado
        if (produto.getQuantidadeEstoque() < quantidade) {
            throw new IllegalArgumentException(
                    "Quantidade indisponível. Estoque atual: " + produto.getQuantidadeEstoque()
            );
        }

        carrinho.atualizarQuantidade(produtoId, quantidade);
        return carrinho;
    }

    @Override
    public Carrinho removerItem(Carrinho carrinho, Long produtoId) {
        carrinho.removerItem(produtoId);
        return carrinho;
    }

    @Override
    public BigDecimal calcularTotal(Carrinho carrinho) {
        return carrinho.getTotal();
    }

    @Override
    public int contarItens(Carrinho carrinho) {
        return carrinho.getTotalItens();
    }
}
